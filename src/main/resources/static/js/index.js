
	
//屏幕宽度限制
$(function () {

    var removeCssAttr = function (elem, attr) {
        var s = elem.style;
        if (s.removeProperty) {
            s.removeProperty(attr);
        } else {
            s.removeAttribute(attr);//兼容低版本ie
        }
    };
    minwidth();
    $(window).load(function () {
        $(window).resize(function () {
            minwidth();
        })
    });



    function minwidth() {
        if ($(window).width() < 1180) {
            $("body").css({ "width": "1200px" });
        } else {
            removeCssAttr(document.body, 'width');
        }
    };
	
	
    if ($(".newsscrollcont").length > 0) {
        $('.newsscrollcont ul').cycle({
            fx: "scrollHorz",
            pagerEvent: "click",
            next: ".nsright",
            prev: ".nsleft",
            speed: 800,
            timeout: 3000,
            pause: 1

        });
    };
	
	 $(".changenewslist h4 a").eq(0).addClass("cur");
    $(".changenewstext").eq(0).show();
    $(".changenewslist h4 a").click(function () {
        var thisindex = $(this).index();
        var thislink = $(this).attr("rel");
        $(".inmore").attr("href", thislink);
        $(this).addClass("cur").siblings().removeClass("cur");
        $(".changenewstext").eq(thisindex).show().siblings(".changenewstext").hide();
    });
      $(".hmkmlist li").hover(function(){
			var index=$(this).index();
			$(".hmzjbox .list-item").hide();
			$(".hmzjbox .list-item").eq(index).show();
			$(".hmkmlist li").removeClass("on");
			$(this).addClass("on");
		});
		
		
	  //-------- 左右高度 --------
    var leftcont = $(".pagesleft").height();
    var righthe = $(".pageright").outerHeight();
    setTimeout(function () {
        if (leftcont < righthe) {
            $(".pagesleft").css("height", righthe);
        }
    }, 100);
	$(".zpgsbox dl").hover(function () {
        $(this).addClass("cur");
    }, function () {
        $(this).removeClass("cur");
    })
});

//侧边快捷链接
var aside=$("#aside");asideService=$("#aside-service>li>a");
asideService.hover(function(){
  $("span",this).css({"display":"block","opacity":0}).stop(true,false).animate({"opacity":1,"right":"40px"},300);
},function(){
  $("span",this).stop(true,false).animate({"opacity":0,"right":"0"},300);
});

//友情链接
$(function(){
$('.footLink').find('.rightBtn').click(function(){
		imgScrollRight2($('.footLink').find('.wal'),$('.footLink').find('li').length-4,251,0);
});
	$('.footLink').find('.leftBtn').click(function(){
		imgScrollLeft2($('.footLink').find('.wal'),$('.footLink').find('li').length-4,251,0);
});
//返回顶部
/*if($('.scroll-to-top').length){
    //Check to see if the window is top if not then display button
    $(window).scroll(function(){
      if ($(this).scrollTop() > 250) {
        $('.scroll-to-top').fadeIn();
      } else {
        $('.scroll-to-top').fadeOut();
      }
    });
    
    //Click event to scroll to top
    $('.scroll-to-top').on('click', function(){
      $('html, body').animate({scrollTop : 0},800);
      return false;
    });
  } */
  //返回顶部
var gotop=$("#gotop");
gotop.click(function(){
  $('html,body').animate({scrollTop:0},500);
  return false;
});
$(window).scroll(function(){
  $(this).scrollTop()>190?gotop.fadeIn():gotop.fadeOut();
});

var totop=$("#totop");
totop.click(function(){
  $('html,body').animate({scrollTop:0},500);
  return false;
});
$(window).scroll(function(){
  $(this).scrollTop()>190?totop.fadeIn():totop.fadeOut();
});
})
   //判断是否为移动端
    var browser = {
        versions: function () {
            var u = navigator.userAgent, app = navigator.appVersion;
            return {//移动终端浏览器版本信息   
                trident: u.indexOf('Trident') > -1, //IE内核  
                presto: u.indexOf('Presto') > -1, //opera内核  
                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核  
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核  
                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端  
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端  
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器  
                iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器  
                iPad: u.indexOf('iPad') > -1, //是否iPad    
                webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部  
            };
        } (),
        language: (navigator.browserLanguage || navigator.language).toLowerCase()
    }

    if (browser.versions.mobile || browser.versions.ios || browser.versions.android ||
    browser.versions.iPhone || browser.versions.iPad) {
        window.location = "m/index.html";
    }


