layui.use(['layer','table','jquery' ],function(){
    var table  = layui.table,
        layer=layui.layer,
        $ = jQuery = layui.$;
    table.render({
        elem:'#bmall'//要绑定的数据表格
        ,url: '/loadAllDoctor' //数据接口
        ,height: 'full' //高度最大化
        ,page: true //开启分页
        ,skin: 'sm' //行边框风格
        ,even: true //开启隔行背景
        ,title:"所有医生" //标题
        ,text:"数据显示异常"
        ,page:true//分页
        ,limits:[1,2,3,5,10,20,30]//显示条数列表
        ,toolbar:"#toolbarBm" //显示工具栏
        ,cols:[[
            {field:"id", align: "center",title:"编号",width:"15%", sort: true},
            {field:"NAME", align: "center",title:"名称",width:"10%", sort: true},
            {field:"sex", align: "center",title:"性别",width:"10%"},
            {field:"age", align: "center",title:"年龄",width:"15%"},
            {field:"comeage", align: "center",title:"工作年龄",width:"15%"},
            {field:"tel", align: "center",title:"电话号码",width:"10%"},
            {field: 'op', title: '操作', width: "20%", align:"center",toolbar:"#barBm"}
        ]]
    });

    //头工具栏事件
    table.on('toolbar(text)', function(obj){
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'add'){
            layer.open({
                type: 2, //弹出层的类型
                anim: 3, //进入的动画方式
                title: '添加医生',
                shadeClose: true,
                shade: 0.8,
                area: ['480px', '90%'],
                content: 'add_stafff',//这里content是一个普通的String
            });
        }
    });
    //监听行
    table.on('tool(text)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
          if(layEvent === 'del'){
            layer.confirm('真的删除该员工么', function(index){
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
                $.ajax({
                    url:"/delStafff/"+data.id,
                    type:"post",
                    success:function(){
                        table.reload("tb",{
                            page:{curr:1},//重载以后，回到第1页
                            where:{time:new Date()}//防止缓存
                        });
                    }
                });
            });
        } else if(layEvent === 'edit'){
              layer.open({
                  type: 2, //弹出层的类型
                  anim: 3, //进入的动画方式
                  title: '修改医生信息',
                  shadeClose: true,
                  shade: 0.8,
                  area: ['480px', '90%'],
                  content: 'add_stafff',//这里content是一个普通的String
                  success: function(layero, index){
                      var body = layui.layer.getChildFrame('body');
                      body.find("[name=id]").val(data.id);
                      body.find("[name=NAME]").val(data.NAME);
                      body.find("[name=sex]").val(data.sex).attr(selected="");
                      body.find("[name=age]").val(data.age);
                      body.find("[name=comeage]").val(data.comeage);
                      body.find("[name=tel]").val(data.tel);
                      body.find("[name=content]").val(data.content);
                      body.find("[name=img]").html("<img src='"+data.photo+"'>")

                  },
                  end: function(){
                      table.reload("tb");//重新加载数据
                      layer.msg("数据修改成功",{icon:1});
                  }
              });
        }
    });
});//layui.use结束标志
