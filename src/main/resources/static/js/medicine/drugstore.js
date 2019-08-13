layui.use(['table', 'layer', 'jquery'], function(){
    var table = layui.table;
    var layer=layui.layer;
    var $=layui.$;
    table.render({
        elem: '#demo'
        ,url: '/drugstore' //数据接口
        ,page: false //开启分页
        ,limit:3 //默认每一页显示的条数
        ,limits:[1,2,3,5,10,20,30,50]//提示的每页条数的列表
        ,toolbar:"#addDemo" //显示工具栏
        ,title:"各库详情" //设置导出文件时的标题
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
            {field:'no',type:'checkbox', width:"10%",fixed: 'left',align:"center"}
            ,{field:'sto_no', title:'库号',width:"10%", sort: true, fixed: 'left',align:"center",}
            ,{field:'sto_count', title: '可存放药品总数', width:"15%",align:"center"}
            ,{field:'sto_surplus', title: '该药品可放数量', width:"15%",align:"center"}
            ,{field:'dru_name', title: '该药品的药品名称', width:"15%",align:"center"}
            ,{field:'dru_drugnum', title: '该药品的存放数量', width:"15%",align:"center"}
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
                title: "各库药品详情",
                content: '/for/drugstore_detail',
                zIndex: layer.zIndex, //重点1
                success: function(layero){
                    layer.setTop(layero); //重点2
                    var body=layui.layer.getChildFrame("body");
                    body.find("[name='sto_no']").val("库"+(data.sto_no));
                    body.find("[name='sto_count']").val(data.sto_count);
                    body.find("[name='dru_name']").val(data.dru_name);
                    body.find("[name='dru_drugnum']").val(data.dru_drugnum);
                    body.find("[name='dru_properties']").val(data.dru_properties);
                    body.find("[name='dru_date']").val(data.dru_date);
                    body.find("[name='dru_specification']").val(data.dru_specification);
                    body.find("[name='dru_data_in']").val(data.dru_data_in);
                    body.find("[name='dru_price']").val(data.dru_price);
                }
            });

        }
    });
});