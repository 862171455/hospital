layui.use(['table', 'layer', 'jquery'], function(){
    var table = layui.table;
    var layer=layui.layer;
    var $=layui.$;
    table.render({
        elem: '#demo'
        ,url: '/sup' //数据接口
        ,page: false//开启分页
        ,toolbar:"#addDemo" //显示工具栏
        ,title:"供应商信息" //设置导出文件时的标题
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
            ,{field:' sup_no', title:'序号',width:"10%", sort: true, fixed: 'left',align:"center",style:'color:#000;',fixed: true,templet:'<div><a class="layui-table-link">{{d.LAY_TABLE_INDEX+1}}</a></div>'}
            ,{field:'sup_unit', title: '供应商单位名', width:"15%",align:"center"}
            ,{field:'sup_address', title: '供应商地址', width:"15%",align:"center"}
            ,{field:'sup_people', title: '供应商联系人', width: "15%",sort: true,align:"center"}
            ,{field:'sup_email', title: '供应商emali', width: "15%", sort: true,align:"center"}
            ,{field:'sup_phone', title: '供应商联系电话', width: "20%", sort: true,align:"center"}

        ]]
    });
});