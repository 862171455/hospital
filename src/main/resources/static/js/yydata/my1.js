$(function(){

    //获得div组件，并且渲染成图表组件
    var echart = echarts.init($("#mydiv2")[0]);


    //设置图表的属性
    var option={
        //大标题
        title:{
            //标题内容
            text:"每日收银",
            //子标题
            subtext:"统计人:GD",
            //对齐方式
            x:"left"
        },
        //显示，柱状的说明信息
        legend:{
            data:['收益']
        },
        //辅助工具
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        //鼠标悬停时的提示信息
        tooltip:{
        },
        //指定x轴
        xAxis:{
            data:[]//标题数据，需要动态加载,1维数组
        },
        //指定y轴
        yAxis:{
        },
        //用于指定要显示的数据
        series:[{
            name:'时间',
            type:"bar",  //bar柱状图,pie饼图，line:折线图
            data:[],//数据，需要动态加载，1维数组
            itemStyle: {//阴影效果
                normal: {
                    shadowBlur: 200,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    };

    //将属性与渲染后的图表关联
    echart.setOption(option);
    /*****************************用ajax加载数据**********************************************************/
    $.ajax({
        url:"/load/data/yy/sy",
        type:"post",
        dataType:"json",
        success:function(data){
            //渲染图表的时，哪里选项，没有赋值，在此处，赋值即可
            echart.setOption({
                xAxis:{
                    data:data.names//标题数据，需要动态加载,1维数组
                },
                series:[{
                    name:'时间',
                    type:"bar",  //bar柱状图,pie饼图，line:折线图
                    data:data.vals,//数据，需要动态加载，1维数组
                    itemStyle: {//阴影效果
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