package com.Newcity.libs.redis;

import com.Newcity.lib.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * 开发及升级规划
 * Redis 管理方案，网上的方案雷同较多，且有缺陷。
 * 专用管理方案，开发工作量过大。
 * 考虑后期扩展和当前开发情况，暂时定出 IRedis 接口。为今后扩展留出空间。
 * V1.0 IRedis =  JedisPool 方式
 * */
public class IRedis {

	//日志
	private Logger logger = LoggerFactory.getLogger( IRedis.class );

	private JedisPool pool = null;
	public JedisPool getPool() {
		return pool;
	}
	public void setPool(JedisPool pool) {
		this.pool = pool;
	}

	private static IRedis iRedis = null;

	private IRedis(){}
	public static IRedis instance(){
		if(iRedis == null){
			return iRedis = new IRedis();
		}
		return iRedis;
	}

	public IRedis(JedisPool pool) {
		this.pool = pool;
	}


	
	public void closeResource( Jedis jedis){
		if (jedis == null) {
			return;
		}
		jedis.close();
		if (jedis.isConnected()) { 
			jedis.disconnect();
		}
	}
	
	/**
	 * 00 获取 Jedis 资源
	 * */
	public synchronized Jedis getResource(){
		Jedis jedis = null;
		try{
			jedis= pool.getResource();
			return jedis;
		}catch(Exception e){
			//重新尝试获取 2 次
			try{
				closeResource( jedis );
				logger.error("#getResource(2)# #########################  :"+pool.getNumActive() + "|"+pool.getNumIdle() +"|"+pool.getNumWaiters() + "|" + pool.isClosed() + "|" + jedis.isConnected() , e ) ;
				jedis = pool.getResource();
				e.printStackTrace();
			}catch(Exception e1){
				try{
					closeResource( jedis );
					logger.error("#getResource(3)# #########################  :"+pool.getNumActive() + "|"+pool.getNumIdle() +"|"+pool.getNumWaiters() + "|" + pool.isClosed() + "|" + jedis.isConnected() , e) ;
					pool.returnBrokenResource(jedis);
					jedis = pool.getResource();
				}catch( Exception e2 ){
					closeResource( jedis );
					logger.error("#getResource(4)# 无法获取链接 :" +pool.getNumActive() + "|"+pool.getNumIdle() +"|"+pool.getNumWaiters() + "|" + pool.isClosed() + "|" + jedis.isConnected() , e) ;
					e.printStackTrace();
				}
			}
		}
		return jedis; 
	}

	/**
	 * 01 释放jedis资源
	 * @param jedis
	 */
	public void returnResource(Jedis jedis) {
		if (jedis == null) {
			return;
		}
		try{
			synchronized (jedis) {
				jedis.close();
				if (jedis.isConnected()) { 
					jedis.disconnect();
				}
			}
		}catch (Exception e) {
			logger.error("GrowFace Jedis Close Error : " + e);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 02 续期
	 */
	public Long expire( String key, int seconds) {
		Jedis jedis = null;
		try { 
			synchronized (jedis = getResource()) {
				return jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			logger.error("GrowFace Expire-Time  07 Err : " + key + "|" + seconds  + "|" + e);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return null;
	}

	/*****************************************************************************************
	 * Hset
	 ****************************************************************************************/
	
	/**
	 * 01 hset
	 * */
	@SuppressWarnings("unused")
	public Long hset( String key,  String field,  String value ) {
		Jedis jedis = null;
		try { 
			synchronized ( jedis = getResource() ) {
				if (jedis == null) {
					return null;
				}
				return jedis.hset(key, field, value);
			}	
		} catch (Exception e) {
			logger.error("GrowFace hset 01 Err : " + key + "|"+field + "|"+value + "|" +e);
			e.printStackTrace();
		} finally { 
			returnResource(jedis);
		}
		return null;
	}
	
	/**
	 *02 hset + expire
	 */
	public Long hset( String key,  String field,  String value ,  Integer expire ) {
		logger.debug("GrowFace hset 02 call: " + key + "|" + field + "|"+value +"|" + expire );
		Jedis jedis = null;
		try { 
			synchronized (jedis = getResource()) {
				return jedis.hset(key, field, value);
			}
		} catch (Exception e) {
			logger.error("GrowFace hset 02 Err : " + key + "|" + field + "|"+value +"|" + expire , e);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return null;
	}
	
	/**
	 * 03  hset +  map  
	 */
	public String hset( String key, Map<String,String> map , Integer expire) {
		Jedis jedis = null;
		try { 
			synchronized (jedis = getResource()) {
				String result = jedis.hmset(key, map) ;
				jedis.expire(key, expire);
				return result;
			}
		} catch (Exception e) {
			logger.error("GrowFace hset4exists 03 Err : " + key + "|"+map +   "|" + expire + "|" +e);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return null;
	}
	
	/**
	 * 04 Hset For exists
	 * Filter 定制业务
	 */
	public Long hset4exists( String key,  String field,  String value ,  Integer expire , Long trace) {
		Long is = -1L;
		Jedis jedis = null;
		try { 
			synchronized ( jedis = getResource() ) { 
				
				if( !jedis.exists( key )){
					logger.warn( "trace["+trace+"] key not exists "+ key + "-"+expire);
					if(!jedis.exists( key )){
						return is;
					}
					logger.error( "trace["+trace+"] key not exists "+ key + "-"+expire+" reload on >>>");
				} 
				
				is = 1L;
				
				jedis.hset(key, field, value );
				
				if( expire != null ){ //续期
					logger.info("trace["+trace+"] 续期"+ key + "-"+expire);
					jedis.expire(key, expire.intValue() );
				}
				
				return is;
			}
		} catch (Exception e) {
			logger.error("GrowFace hset4exists 04 Err : " + key + "|"+field + "|"+value + "|" + expire + "|" +e);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return null;
	}
	
	
	/**
	 * 05 exists
	 */
	public Boolean exists( String key) {
		Jedis jedis = null;
		try { 
			synchronized (jedis = getResource()) {
				return jedis.exists(key);
			}
		} catch (Exception e) {
			logger.error("GrowFace exists 05 Err : " + key  + "|" + e);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return null;
	}
	
	/**
	 * 05 Auth(定制业务)
	 */
	public Long auth( String key  , Integer expire , Long trace) {
		Jedis jedis = null;
		try { 
			synchronized ( jedis = getResource() ) { 
				
				Map<String,String> map = jedis.hgetAll( key );
				if( map == null){
					logger.warn( "trace["+trace+"] key not exists "+ key + "-"+expire);
					return -1L;
				} 
				
				String state = map.get("state");
				if( state == null ){
					logger.warn( "trace["+trace+"] key not exists state attribute token["+ key + "] state is null - expire"+expire);
					return -2L;
				}
				
				if( !state.equals("1")){
					logger.warn( "trace["+trace+"] key not exists state attribute token["+ key + "] state[" + state + "]-"+expire );
					return -3L;
				}
				
				jedis.hset( key, "refresh_time" , DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss.SSS") );
				
				if( expire != null ){ //续期
					logger.info("trace["+trace+"] 续期"+ key + "-"+expire);
					jedis.expire(key, expire.intValue() );
				}
				
				return 1L;
			}
		} catch (Exception e) {
			logger.error("GrowFace hset4exists 04 Err : " + key +  "|" + expire + "|" , e);
			e.printStackTrace();
			return -98L;
		} finally {
			returnResource(jedis);
		}
	}
 
	/**
	 * 06 exists field
	 */
	public Boolean hexists( String key,  String field) {
		Jedis jedis = null;
		try { 
			synchronized (jedis = getResource()) {
				return jedis.hexists(key, field);
			}
		} catch (Exception e) {
			logger.error("GrowFace exists 06 Err : " + key + "|" + field  + "|" + e);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return null;
	}
	
	


	/**
	 * 01 Get field value
	 */
	public String hget(  String key,   String field) {
		Jedis jedis = null;
		try { 
			synchronized (jedis = getResource()) {
				return jedis.hget(key, field);
			}
		} catch (Exception e) {
			logger.error("GrowFace hget 01 Err : " + key + "|" + field  + "|"  , e);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return null;
	}



	/**
	 * 02 Get field value
	 */
	public Map<String, String> hgetAll( String key) {
		Jedis jedis = null;
		try { 
			synchronized (jedis = getResource()) {
				return jedis.hgetAll(key);
			}
		} catch (Exception e) {
			logger.error("GrowFace hgetAll 02 Err : " + key +  "|"  , e);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return null;
	}
	

	/**
	 * 01 del
	 */
	public Long del( String... keys) {
		Jedis jedis = null;
		try { 
			synchronized (jedis = getResource()) {
				return jedis.del(keys);
			}
		} catch (Exception e) {
			logger.error("GrowFace hgetAll 02 Err : " + keys +  "|" + e);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return null;
	}

	
	/**
	 * 02 del
	 */
	public Long del(String key) {
		Jedis jedis = null;
		try {
			synchronized (jedis = getResource()) {
				return jedis.del(key);
			}
		} catch (Exception e) {
			logger.error("GrowFace hgetAll 02 Err : " + key +  "|" + e);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return null;
	}
	
	/**
	 * 03 hdel
	 */
	public Long hdel(final String key, final String... fields) {
		Jedis jedis = null;
		try {
			synchronized (jedis = getResource()) {
				return jedis.hdel(key, fields);
			}
		} catch (Exception e) {
			logger.error("GrowFace hdel  03 Err : " + key + "|"  + fields +  "|" + e);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return null;
	}


	/*****************************************************************************************
	 *  Single Object
	 ****************************************************************************************/
	
	/**
	 * 01 set
	 */
	public String set( String key, String value) {
		Jedis jedis = null;
		try {
			synchronized (jedis = getResource()) {
				jedis.set(key, value) ;
			}
		} catch (Exception e) {
			logger.error("GrowFace set  02 Err : " + key +  "|" + e);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return null;
	}
	
	
	/**
	 * 02 get
	 */
	public String get( String key) {
		Jedis jedis = null;
		try {
			synchronized (jedis = getResource()) {
				return jedis.get(key);
			}
		} catch (Exception e) {
			logger.error("GrowFace get  01 Err : " + key +  "|" + e);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return null;
	}

	/**
	 * 自增
	 * @param key
	 * @param length  返回长度，不够则补0
     * @return
     */
	public String incr(String key,Integer length){
		Jedis jedis = null;
		try {
			synchronized (jedis = getResource()) {
				String seq = String.valueOf(jedis.incr(key));
				if(seq.length() < length){
					String returns = "";
					for(int i=0;i< length - seq.length();i++){
						returns += "0";
					}
					return returns + seq;
				}else{
					return seq;
				}
			}
		} catch (Exception e) {
			logger.error("GrowFace get  01 Err : " + key +  "|" + e);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return null;
	}

} 