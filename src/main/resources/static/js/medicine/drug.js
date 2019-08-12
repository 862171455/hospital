layui.use(['table', 'layer', 'jquery'], function(){
    var table = layui.table;
    var layer=layui.layer;
    var $=layui.$;
    table.render({
        elem: '#demo'
        ,url: '/drug' //数据接口
        ,page: true //开启分页
        ,limit:3 //默认每一页显示的条数
        ,limits:[1,2,3,5,10,20,30,50]//提示的每页条数的列表
        ,toolbar:"#addDemo" //显示工具栏
        ,title:"药品信息" //设置导出文件时的标题
        ,loading:true
        ,done: function (res, curr, count) {// 表格渲染完成之后的回调
            var that = this.elem.next();
            res.data.forEach(function (item, index) {
                //console.log(item.empName);item表示每列显示的数据
                if (index%2==0) {
                    var tr = that.find(".layui-table-box tbody tr[data-index='" + index + "']").css("background-color","#97FFFF");
                } else{
                    var tr = that.find(".layui-table-box tbody tr[data-index='" + index + "']").css("background-color", "#ffb8c6");
                }
            });
        }

        ,cols: [[ //表头
            {field:'no',type:'checkbox', width:"3%",fixed: 'left',align:"center"}
            ,{field:'dru_no', title:'序号',width:"3%", sort: true, fixed: 'left',align:"center",style:'color:#000;',fixed: true,templet:'<div><a class="layui-table-link">{{d.LAY_TABLE_INDEX+1}}</a></div>'}
            ,{field:'dru_name', title: '药品名称', width:"10%",align:"center"}
            ,{field:'dru_specification', title: '药品规格', width:"10%",align:"center"}
            ,{field:'dru_date', title: '出厂日期', width: "10%",sort: true,align:"center"}
            ,{field:'dru_factory', title: '生产厂家', width: "10%", sort: true,align:"center"}
            ,{field:'dru_data_in', title: '入库时间', width: "10%", sort: true,align:"center"}
            ,{field:'dru_release', title: '保质期', width: "9%", sort: true,align:"center"}
            ,{field:'dru_price', title: '单价', width: "7%", sort: true,align:"center"}
            ,{field:'dru_drugnum', title: '药品数量', width: "10%", sort: true,align:"center"}
            ,{field:'dru_properties', title: '药品用途', width: "10%", sort: true,align:"center"}
            ,{field:'op', title: '操作', width: "15%", sort: true,align:"center",toolbar:"#barDemo"}
        ]]
    });
    table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if(layEvent === 'detail'){ //查看
            layer.open({
                type: 2,
                shade: [0.8, '#393D49'],
                area: ['500px', '400px'],
                maxmin: false,
                anim: 6,
                title: "药品详情",
                content: '/for/detail',
                zIndex: layer.zIndex, //重点1
                success: function(layero){
                    layer.setTop(layero); //重点2
                    var body=layui.layer.getChildFrame("body");
                    body.find("[name='dru_name']").val(data.dru_name);
                    body.find("[name='dru_specification']").val(data.dru_specification);
                    body.find("[name='dru_properties']").val(data.dru_properties);
                    body.find("[name='dru_factory']").val(data.dru_factory);
                    body.find("[name='dru_release']").val((data.dru_release)+'个月');
                    body.find("[name='dru_price']").val((data.dru_price)+'元');
                    body.find("[name='dru_drugnum']").val(data.dru_drugnum );
                    body.find("[name='dru_date']").val(data.dru_date);
                    body.find("[name='dru_data_in']").val(format(data.dru_data_in, 'yyyy-MM-dd'));
                }
            });
        } else if(layEvent === 'del'){ //删除
            layer.confirm('真的删除这行数据么', function(index){
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存

                $.post("/del",{"dru_no":data.dru_no},function () {
                    table.reload('demo', {
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        //只重载数据


                    });
                    layer.close(index);
                });
                //向服务端发送删除指令
            });
        } else if(layEvent === 'edit'){ //编辑
            layer.open({
                type: 2,
                shade: [0.8, '#393D49'],
                area: ['500px', '400px'],
                maxmin: false,
                anim: 6,
                title: "修改药品信息",
                content: '/for/edit',
                zIndex: layer.zIndex, //重点1
                success: function(layero){
                    layer.setTop(layero); //重点2
                    var body=layui.layer.getChildFrame("body");
                    body.find("[name='dru_no']").val(data.dru_no);
                    body.find("[name='dru_name']").val(data.dru_name);
                    body.find("[name='dru_specification']").val(data.dru_specification);
                    body.find("[name='dru_properties']").val(data.dru_properties);
                    body.find("[name='dru_factory']").val(data.dru_factory);
                    body.find("[name='dru_release']").val(data.dru_release);
                    body.find("[name='dru_price']").val(data.dru_price);
                    body.find("[name='dru_drugnum']").val(data.dru_drugnum );
                    body.find("[name='dru_date']").val(data.dru_date);
                    body.find("[name='dru_drugstore_no']").val(data.dru_drugstore_no);
                    body.find("[name='dru_data_in']").val(format(data.dru_data_in, 'yyyy-MM-dd'));
                }
            });

            //同步更新缓存对应的值

        }
    });
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
            ,data = checkStatus.data; //获取选中的数据
        switch(obj.event) {
            case 'add':
                layer.open({
                    type: 2,
                    shade: [0.8, '#393D49'],
                    area: ['500px', '400px'],
                    maxmin: false,
                    anim: 6,
                    title: "添加药品",
                    content: '/for/add',
                    zIndex: layer.zIndex, //重点1
                    success: function(layero){
                        layer.setTop(layero); //重点2
                    }
                });
                break;
        }
    });
    var format = function (time, format) {
        var t = new Date(time);
        var tf = function (i) {
            return (i < 10 ? '0' : '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
            switch (a) {
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        })

    }
    $("#search").click(function () {
        var dru_name=$("#dru_name").val();
        var dru_drugstore_no=$("#dru_drugstore_no").val();
        table.reload('demo', {
            where:{
                dru_name:dru_name
                ,dru_drugstore_no:dru_drugstore_no
            }


            , page: {
                curr: 1 //重新从第 1 页开始
            }
            //只重载数据


        });
    })
    $("#reset").click(function () {
        $("input").val("");
    });
});