/**
 * Created by dev on 2016/5/12.
 */
    $(function () {
        //删除表单
        $('#workdate_list').delegate('i','click',function(){
            var val = $("#workTime").val();
            var inputVal = $(this).prev().val();
            val = val.replace(inputVal+",","");
            val = val.replace(","+inputVal,"");
            val = val.replace(inputVal,"");
            $("#workTime").val(val);
            $(this).parent().remove();
            var inputs = $('#workdate_list input');
            if(inputs.length<3){
                $('#add_workdate').show();
            }
            if(inputs.length == 0){
                $('#allTime')[0].checked = true;
                $('#allTime').val(1);
                $('#allTime_div').show();
            }
        });

        //以下是时间选择控件
        $('#workdate_list').delegate('.timeRange','click',function(){
            $('#timeRange_div').remove();
            var hourOpts = '';
            for (i = 0; i < 24; i++) hourOpts += '<option>' + i + '</option>';
            var minuteOpts = '<option>00</option><option>10</option><option>20</option><option>30</option><option>40</option><option>50</option>';

            var html = $('<div id="timeRange_div"><select id="timeRange_a" style="width:auto;">' + hourOpts +
                '</select>:<select id="timeRange_b" style="width:auto;">' + minuteOpts +
                '</select>-<select id="timeRange_c" style="width:auto;">' + hourOpts +
                '</select>:<select id="timeRange_d" style="width:auto;">' + minuteOpts +
                '</select><input type="button" value="确定" id="timeRange_btn" /></div>')
                .css({
                    "position": "absolute",
                    "z-index": "999",
                    "padding": "5px",
                    "border": "1px solid #AAA",
                    "background-color": "#FFF",
                    "box-shadow": "1px 1px 3px rgba(0,0,0,.4)"
                })
                .click(function () {
                    return false
                });
            // 如果文本框有值
            var v = $(this).val();
            if (v) {
                v = v.split(/:|-/);
                html.find('#timeRange_a').val(v[0]);
                html.find('#timeRange_b').val(v[1].replace(/(^\s*)|(\s*$)/g,''));
                html.find('#timeRange_c').val(v[2].replace(/(^\s*)|(\s*$)/g,''));
                html.find('#timeRange_d').val(v[3]);
            }
            // 点击确定的时候
            var pObj = $(this);
            html.find('#timeRange_btn').click(function () {
                var start = parseInt(html.find('#timeRange_a').val()+html.find('#timeRange_b').val());

                var end = parseInt(html.find('#timeRange_c').val()+html.find('#timeRange_d').val());
                if(start>=end) {
                    alert('请选择正确的上班时段!')
                    return ;
                }

                var str = html.find('#timeRange_a').val() + ':'
                    + html.find('#timeRange_b').val() + ' - '
                    + html.find('#timeRange_c').val() + ':'
                    + html.find('#timeRange_d').val();
                pObj.val(str);
                $('#timeRange_div').remove();
            });

            $(this).after(html);
            return false;
        })


        //添加时间
        $("#workTime").click(function(){
            //alert("ok")
            $('#timeRange_div').remove();
            var hourOpts = '';
            for (i = 0; i < 24; i++) hourOpts += '<option>' + i + '</option>';
            var minuteOpts = '<option>00</option><option>10</option><option>20</option><option>30</option><option>40</option><option>50</option>';

            var html = $('<div id="timeRange_div"><select id="timeRange_a" style="width:auto;">' + hourOpts +
                '</select>:<select id="timeRange_b" style="width:auto;">' + minuteOpts +
                '</select>-<select id="timeRange_c" style="width:auto;">' + hourOpts +
                '</select>:<select id="timeRange_d" style="width:auto;">' + minuteOpts +
                '</select><input type="button" value="确定" id="timeRange_btn" /></div>')
                .css({
                    //"position": "absolute",
                    "z-index": "999",
                    "padding": "5px",
                    "border": "1px solid #AAA",
                    "background-color": "#FFF",
                    "box-shadow": "1px 1px 3px rgba(0,0,0,.4)"
                })
                .click(function () {
                    return false
                });
            // 点击确定的时候
            var pObj = $(this);
            html.find('#timeRange_btn').click(function () {
                var start = parseInt(html.find('#timeRange_a').val()+html.find('#timeRange_b').val());
                var end = parseInt(html.find('#timeRange_c').val()+html.find('#timeRange_d').val());
                if(start>=end) {
                    alert('请选择正确的上班时段!')
                    return ;
                }
                var str = html.find('#timeRange_a').val() + ':'
                    + html.find('#timeRange_b').val() + ' - '
                    + html.find('#timeRange_c').val() + ':'
                    + html.find('#timeRange_d').val();
                var val = pObj.val();
                if(val != '') {
                    val = val + ","+str;
                }else{
                    val = str;
                }
                pObj.val(val);
                $('#timeRange_div').remove();
                if(true){
                    html=' <div style="margin-bottom: 10px;"><input class="timeRange" type="text" readonly="readonly" value="'+str+'"> <i class="icon-remove"></i></div>';
                    $("#workdate_list").append(html)
                    var inputs = $('#workdate_list input');
                    if(inputs.length>=3){
                        $('#add_workdate').hide();
                    }else if(inputs.length == 1){
                        $('#allTime')[0].checked = false;
                        $('#allTime').val(0);
                        $('#allTime_div').hide();
                    }
                }
            });
            $(this).after(html);
            return false;
        });
        //
        $(document).click(function () {
            $('#timeRange_div').remove();
        });
        //
    });