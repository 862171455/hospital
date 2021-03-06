layui.use(['layer','table','jquery' ],function(){
    var table  = layui.table,
        layer=layui.layer,
        $ = jQuery = layui.$;
    table.render({
        elem:'#bmall'//要绑定的数据表格
        ,url: '/loadAllRoom' //数据接口
        ,height: 'full' //高度最大化
        ,page: true //开启分页
        ,skin: 'sm' //行边框风格
        ,even: true //开启隔行背景
        ,title:"" //标题
        ,text:"数据显示异常"
        ,page:true//分页
        ,limits:[1,2,3,5,10,20,30]//显示条数列表
        ,toolbar:"#toolbarBm" //显示工具栏
        ,cols:[[
            {field:"room_id", align: "center",title:"编号",width:"8%", sort: true},
            {field:"room_lc", align: "center",title:"楼层",width:"15%", sort: true},
            {field:"room_name", align: "center",title:"房间名",width:"17%"},
            {field:"room_con", align: "center",title:"简介",width:"25%",},
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
                title: '添加',
                shadeClose: true,
                shade: 0.8,
                area: ['480px', '90%'],
                content: 'add_room',//这里content是一个普通的String
            });
        }
    });
    //监听行
    table.on('tool(text)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
          if(layEvent === 'del'){
            layer.confirm('真的删除么', function(index){
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
                $.ajax({
                    url:"/delRoom/"+data.room_id,
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
                  title: '修改信息',
                  shadeClose: true,
                  shade: 0.8,
                  area: ['480px', '90%'],
                  content: 'add_room',//这里content是一个普通的String
                  success: function(layero, index){
                      var body = layui.layer.getChildFrame('body');
                      body.find("[name=room_id]").val(data.room_id);
                      body.find("[name=room_lc]").val(data.room_lc);
                      body.find("[name=room_name]").val(data.room_name);
                      body.find("[name=room_con]").val(data.room_con);
                  },
                  end: function(){
                      table.reload("tb");//重新加载数据
                      layer.msg("数据修改成功",{icon:1});
                  }
              });
        }
    });
});//layui.use结束标志
