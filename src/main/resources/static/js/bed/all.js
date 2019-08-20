layui.use(['layer','table','jquery','form' ],function(){
    var table  = layui.table,
        layer=layui.layer,
        form = layui.form,
        $ = jQuery = layui.$;
    table.render({
        elem:'#bmall'//要绑定的数据表格
        ,url: '/loadAllbeed' //数据接口
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
            {field:"bed_id", align: "center",title:"编号",width:"8%", sort: true},
            {field:"room_name", align: "center",title:"房间名称",width:"17%", sort: true},
            {field:"bed_type", align: "center",title:"是否被使用", templet: '#checkboxTpl', width:"18%" },
            {field:"bed_money", align: "center",title:"每月/元",width:"20%"},
            {field: 'op', title: '操作', width: "20%", align:"center",toolbar:"#barBm"}
        ]]
    });
    form.on('checkbox(lockDemo)', function(obj){
        layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
        if(obj.elem.checked){ //修改成已使用
            $.ajax({
                url:"/upbedtype/"+this.value+"/"+1,
                type:"post",
                success:function(){
                    table.reload("tb",{
                        page:{curr:1},//重载以后，回到第1页
                        where:{time:new Date()}//防止缓存
                    });
                }
            });
        }else{//修改成不能被使用
            $.ajax({
                url:"/upbedtype/"+this.value+"/"+2,
                type:"post",
                success:function(){
                    table.reload("tb",{
                        page:{curr:1},//重载以后，回到第1页
                        where:{time:new Date()}//防止缓存
                    });
                }
            });
        }

    });

    //头工具栏事件
    table.on('toolbar(text)', function(obj){
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'add'){
            layer.open({
                type: 2, //弹出层的类型
                anim: 3, //进入的动画方式
                title: '添加部门',
                shadeClose: true,
                shade: 0.8,
                area: ['480px', '90%'],
                content: 'add_bed',//这里content是一个普通的String
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
                    url:"/delbed/"+data.bed_id,
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
                  content: 'add_bed',//这里content是一个普通的String
                  success: function(layero, index){
                      var body = layui.layer.getChildFrame('body');
                      body.find("[name=bed_id]").val(data.bed_id);
                      body.find("[name=bed_money]").val(data.bed_money);
                      body.find("[name=bed_room_id]").val(data.bed_room_id);
                  },
                  end: function(){
                      table.reload("tb");//重新加载数据
                      layer.msg("数据修改成功",{icon:1});
                  }
              });
        }
    });
});//layui.use结束标志
