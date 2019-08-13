layui.use([ 'form', 'layer','jquery' ], function() {
        var form = layui.form,
            layer = layui.layer,
            $ = jQuery = layui.$;
        form.on('submit(*)',function(){
            $.ajax({
                url:"/addDivision",
                type:"post",
                data:$("#mybm").serialize(),
                success:function(){
                    //修改完成，刷新父窗口,一刷新弹出层会自动关闭
                    window.parent.location.reload();

                }
            });
            //忽略掉提交事件
            return false;
        });
    });

//点击，关闭弹出层，不刷新页面
function myclose(){
    //alert("####");
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
}

