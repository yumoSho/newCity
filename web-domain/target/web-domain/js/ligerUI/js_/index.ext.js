	var tab = null;
	var accordion = null;
	var tree = null;
	var tabItems = [];
	$(function () {
 		//setTimeout( "loadModuleData()" , 1000);
		$("#layout1").ligerLayout({ leftWidth: 190, height: '100%',heightDiff:-34,space:4, onHeightChanged: f_heightChanged });

		var height = $(".l-layout-center").height();

		//Tab
		$("#framecenter").ligerTab({
			height: height,
			showSwitchInTab : true,
			showSwitch: true,
			onAfterAddTabItem: function (tabdata)
			{
				tabItems.push(tabdata);
				saveTabStatus();
			},
			onAfterRemoveTabItem: function (tabid)
			{ 
				for (var i = 0; i < tabItems.length; i++)
				{
					var o = tabItems[i];
					if (o.tabid == tabid)
					{
						tabItems.splice(i, 1);
						saveTabStatus();
						break;
					}
				}
			},
			onReload: function (tabdata)
			{
				var tabid = tabdata.tabid;
				addFrameSkinLink(tabid);
			}
		});
 
		$("#accordion1").ligerAccordion({ height: height - 24, speed: null });

		$(".l-link").hover(function () {
			$(this).addClass("l-link-over");
		}, function () {
			$(this).removeClass("l-link-over");
		});
		
		
		$("#tree1").ligerTree({
			data : tree_system,
			checkbox: false,
			slide: false,
			nodeWidth: 120,
			attribute: ['nodename', 'url'],
			onSelect: function (node)
			{ 
				if (!node.data.url) return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid)
				{	//alert( node.target );
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid)
				} 
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
		
		
 
		$("#tree2").ligerTree({
			data : tree_account,
			checkbox: false,
			slide: true,
			nodeWidth: 120,
			attribute: ['nodename', 'url'],
			onSelect: function (node)
			{
				if (!node.data.url) return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid)
				{
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid)
				} 
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
		
 
		$("#tree3").ligerTree({
			data : tree_operation ,
			checkbox: false,
			slide: false,
			nodeWidth: 120,
			attribute: ['nodename', 'url'],
			onSelect: function (node)
			{
				if (!node.data.url) return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid)
				{
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid)
				} 
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
 
		$("#tree4").ligerTree({
			data : tree_ad ,
			checkbox: false,
			slide: false,
			nodeWidth: 120,
			attribute: ['nodename', 'url'],
			onSelect: function (node)
			{
				if (!node.data.url) return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid)
				{
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid)
				} 
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});

		tab = liger.get("framecenter");
		accordion = liger.get("accordion1");
		tree = liger.get("tree1");
		$("#pageloading").hide();
		 
		
		css_init();
		pages_init();
	});
	function f_heightChanged(options)
	{  
		if (tab)
			tab.addHeight(options.diff);
		if (accordion && options.middleHeight - 24 > 0)
			accordion.setHeight(options.middleHeight - 24);
	}
	

	var menuIndex = new Array();	
	
	function f_addTab(tabid, text, url)
	{	
		
		//chenhui , 缓存地址；
		var index = menuIndex[text];
		if( index  ){
			//alert("YES:" + index);
			tabid = index;	
		}else{
			//alert("NO:"+tabid);
			menuIndex[text] = tabid;
		} 
		
		//chenhui 	
		if( url ){
			var pre = url.substr(0,3);
			 
			if(pre == "pro"){
				url = "/web-domain/"+url;
			}
		}
		tab.addTabItem({
			tabid: tabid,
			text: text,
			url: url,
			callback: function ()
			{	 
				//addShowCodeBtn(tabid); 
				addFrameSkinLink(tabid); 
			}
		});
	}
	function addShowCodeBtn(tabid)
	{
		var viewSourceBtn = $('<a class="viewsourcelink" href="javascript:void(0)">�鿴Դ��</a>');
		var jiframe = $("#" + tabid);
		viewSourceBtn.insertBefore(jiframe);
		viewSourceBtn.click(function ()
		{
			showCodeView(jiframe.attr("src"));
		}).hover(function ()
		{
			viewSourceBtn.addClass("viewsourcelink-over");
		}, function ()
		{
			viewSourceBtn.removeClass("viewsourcelink-over");
		});
	}
	function showCodeView(src)
	{
		$.ligerDialog.open({
			title : '',
			url: '',
			width: $(window).width() *0.9,
			height: $(window).height() * 0.9
		});

	}
	function addFrameSkinLink(tabid)
	{
		var prevHref = getLinkPrevHref(tabid) || ""; 
		var skin = getQueryString("skin");
		if (!skin) return;
		skin = skin.toLowerCase();
		attachLinkToFrame(tabid, prevHref + skin_links[skin]);
	}
	/*
	var skin_links = {
		"aqua": 	"../skins/Aqua/css/ligerui-all.css",
		"gray": 	"../skins/Gray/css/all.css",
		"silvery": 	"../skins/Silvery/css/style.css",
		"gray2014": "../skins/gray2014/css/all.css"
	};*/
	
	var skin_links = {
		"aqua": 	"../js/ligerUI-1.3.3/skins/Aqua/css/ligerui-all.css",
		"gray": 	"../js/ligerUI-1.3.3/skins/Gray/css/all.css",
		"silvery": 	"../js/ligerUI-1.3.3/skins/Silvery/css/style.css",
		"gray2014": "../js/ligerUI-1.3.3/skins/gray2014/css/all.css"
	}
	function pages_init()
	{
		var tabJson = $.cookie('liger-home-tab'); 
		if (tabJson)
		{ 
			var tabitems = JSON2.parse(tabJson);
			for (var i = 0; tabitems && tabitems[i];i++)
			{ 
				console.log( tabitems[i].tabid + "|" + tabitems[i].text + "|" +  tabitems[i].url);
				f_addTab(tabitems[i].tabid, tabitems[i].text, tabitems[i].url);
			} 
		}
	}
	function saveTabStatus()
	{ 
		$.cookie('liger-home-tab', JSON2.stringify(tabItems));
	}
	function css_init()
	{
		var css = $("#mylink").get(0), skin = getQueryString("skin");
		$("#skinSelect").val(skin);
		//alert($("#skinSelect").val());
		$("#skinSelect").change(function ()
		{ 
			if (this.value)
			{
				location.href = "index.jsp?skin=" + this.value;
			} else
			{
				location.href = "index.jsp";
			}
		});

	   
		if (!css || !skin) return;
		skin = skin.toLowerCase();
		$('body').addClass("body-" + skin); 
		$(css).attr("href", skin_links[skin]); 
	}
	function getQueryString(name)
	{
		var now_url = document.location.search.slice(1), q_array = now_url.split('&');
		for (var i = 0; i < q_array.length; i++)
		{
			var v_array = q_array[i].split('=');
			if (v_array[0] == name)
			{
				return v_array[1];
			}
		}
		return false;
	}
	function attachLinkToFrame(iframeId, filename)
	{ 
		if(!window.frames[iframeId]) return;
		var head = window.frames[iframeId].document.getElementsByTagName('head').item(0);
		var fileref = window.frames[iframeId].document.createElement("link");
		if (!fileref) return;
		fileref.setAttribute("rel", "stylesheet");
		fileref.setAttribute("type", "text/css");
		fileref.setAttribute("href", filename); 
		head.appendChild(fileref);
	}
	function getLinkPrevHref(iframeId)
	{
		if (!window.frames[iframeId]) return;
		var head = window.frames[iframeId].document.getElementsByTagName('head').item(0);
		var links = $("link:first", head);
		for (var i = 0; links[i]; i++)
		{
			var href = $(links[i]).attr("href");
			 
			if (href && href.toLowerCase().indexOf("ligerui") > 0)
			{
				return href.substring(0, href.toLowerCase().indexOf("lib") );
			}
		}
	}
	
	
	function loadModuleData(){ 
	 
		var moduleId = 1;
		var jsonStr = "";
		$.ajax({
			type : 'POST',  
			contentType : 'application/json', 
			url: '../SysModule/tree4role?Math=' + Math.random()  ,
			processData : false,
			dataType : 'json', 
			data: jsonStr ,    
			success: function(data){
				console.log( JSON.stringify(data) );
				
				$("#tree3").ligerTree({
					data : data,
					checkbox: false,
					slide: true,
					nodeWidth: 120,
					attribute: ['nodename', 'url'],
					
					onSelect: function (node) { 
						if (!node.data.url) return;
						var tabid = $(node.target).attr("tabid");
						var url = node.data.url;
					 
						if (!tabid){
							tabid = new Date().getTime();
							$(node.target).attr("tabid", tabid)
						}   
						f_addTab(tabid, node.data.text,  url );
					} ,
					
				}); 
			} ,
			error: HttpErr ,
			complete:function(){
				$("#grid-body").height($(window).height()-69);	
			} 
		});  
		
		
	}
	
	function HttpErr(xhr , status , msg ){
		
		if(xhr.status && xhr.status ){
			HttpErrProcess(xhr.status);
		}else{
			HttpErrProcess( status);
		}
		 
	}