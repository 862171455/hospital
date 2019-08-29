//加载饼图
$(function(){

    //获得div组件，并且渲染成图表组件
    var echart = echarts.init($("#mydiv1")[0]);

    var option={
        //标题
        title:{
            text:"剩余药品",
            subtext:"统计人:GD",
            x:"center"
        },
        //工具栏
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        //每一个扇区的说明信息
        legend:{
            data:[],//标题，需要动态加载，1维数组
            orient: 'vertical',
            x:"left"
        },
        //悬 停的提示信息
        tooltip:{
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        //要显示的数据
        series:[{
            name:'销量',
            type:"pie",
            //roseType: 'angle',//南丁格尔
            radius:"55%",//圆的半径
            data:[],//数据，需要动态加载，2维数组
            itemStyle: {
                normal: {
                    shadowBlur: 200,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }

        }]
    };

    //将属性与渲染后的图表关联
    echart.setOption(option);
    /*************************采用ajax动态加载数据****************************************************/
    $.ajax({
        url:"/load/data/yy/yao",
        type:"post",
        dataType:"json",
        success:function(data){
            //根据返回来的数据，绑定到对应的图表中
            echart.setOption({
                //哪些属性，没有值，就给哪些属性赋值
                legend:{
                    data:data.titles,//标题，需要动态加载，1维数组
                    orient: 'vertical',
                    x:"left"
                },
                //要显示的数据
                series:[{
                    name:'剩余',
                    type:"pie",
                    //roseType: 'angle',//南丁格尔
                    radius:"55%",//圆的半径
                    data:data.vals,//数据，需要动态加载，2维数组
                    itemStyle: {
                        normal: {
                            shadowBlur: 200,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }]
            });
        }

    });

});