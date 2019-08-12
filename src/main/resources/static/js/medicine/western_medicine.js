layui.use(['table', 'layer', 'jquery'], function(){
    var table = layui.table;
    var layer=layui.layer;
    var $=layui.$;
    table.render({
        elem: '#demo'
        ,url: '/western_medicine' //数据接口
        ,page: false //开启分页
        ,limit:3 //默认每一页显示的条数
        ,limits:[1,2,3,5,10,20,30,50]//提示的每页条数的列表
        ,toolbar:"#addDemo" //显示工具栏
        ,title:"西药信息" //设置导出文件时的标题
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
            ,{field:'wes_no', title:'序号',width:"10%", sort: true, fixed: 'left',align:"center",style:'color:#000;',fixed: true,templet:'<div><a class="layui-table-link">{{d.LAY_TABLE_INDEX+1}}</a></div>'}
            ,{field:'wes_name', title: '西药名称', width:"20%",align:"center"}
            ,{field:'sup_unit', title: '所属的供应商单位', width:"20%",align:"center"}
            ,{field:'sup_address', title: '所属的供应商地址', width: "20%",sort: true,align:"center"}
            ,{field:'op', title: '操作', width: "20%", sort: true,align:"center",toolbar:"#barDemo"}
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
                title: "西药详情",
                content: '/for/western_detail',
                zIndex: layer.zIndex, //重点1
                success: function(layero){
                    layer.setTop(layero); //重点2
                    var body=layui.layer.getChildFrame("body");
                    body.find("[name='wes_name']").val(data.wes_name);
                    body.find("[name='sup_unit']").val(data.sup_unit);
                    body.find("[name='sup_address']").val(data.sup_address);
                    body.find("[name='sup_people']").val(data.sup_people);
                    body.find("[name='sup_email']").val(data.sup_email);
                    body.find("[name='sup_phone']").val(data.sup_phone);
                }
            });

        }
    });
});