/*! waitForImages jQuery Plugin 2016-01-04 */
!function(a){"function"==typeof define&&define.amd?define(["jquery"],a):"object"==typeof exports?module.exports=a(require("jquery")):a(jQuery)}(function(a){var b="waitForImages";a.waitForImages={hasImageProperties:["backgroundImage","listStyleImage","borderImage","borderCornerImage","cursor"],hasImageAttributes:["srcset"]},a.expr[":"]["has-src"]=function(b){return a(b).is('img[src][src!=""]')},a.expr[":"].uncached=function(b){return a(b).is(":has-src")?!b.complete:!1},a.fn.waitForImages=function(){var c,d,e,f=0,g=0,h=a.Deferred();if(a.isPlainObject(arguments[0])?(e=arguments[0].waitForAll,d=arguments[0].each,c=arguments[0].finished):1===arguments.length&&"boolean"===a.type(arguments[0])?e=arguments[0]:(c=arguments[0],d=arguments[1],e=arguments[2]),c=c||a.noop,d=d||a.noop,e=!!e,!a.isFunction(c)||!a.isFunction(d))throw new TypeError("An invalid callback was supplied.");return this.each(function(){var i=a(this),j=[],k=a.waitForImages.hasImageProperties||[],l=a.waitForImages.hasImageAttributes||[],m=/url\(\s*(['"]?)(.*?)\1\s*\)/g;e?i.find("*").addBack().each(function(){var b=a(this);b.is("img:has-src")&&!b.is("[srcset]")&&j.push({src:b.attr("src"),element:b[0]}),a.each(k,function(a,c){var d,e=b.css(c);if(!e)return!0;for(;d=m.exec(e);)j.push({src:d[2],element:b[0]})}),a.each(l,function(a,c){var d=b.attr(c);return d?void j.push({src:b.attr("src"),srcset:b.attr("srcset"),element:b[0]}):!0})}):i.find("img:has-src").each(function(){j.push({src:this.src,element:this})}),f=j.length,g=0,0===f&&(c.call(i[0]),h.resolveWith(i[0])),a.each(j,function(e,j){var k=new Image,l="load."+b+" error."+b;a(k).one(l,function m(b){var e=[g,f,"load"==b.type];return g++,d.apply(j.element,e),h.notifyWith(j.element,e),a(this).off(l,m),g==f?(c.call(i[0]),h.resolveWith(i[0]),!1):void 0}),j.srcset&&(k.srcset=j.srcset),k.src=j.src})}),h.promise()}});

jQuery(document).ready(function($) {
    // 手机菜单
    $(".menu-m").click(function(){
        if($(this).hasClass('ok')){
            $(this).removeClass('ok');
            $("#hd").removeClass('hd-ani');
            $(".view").removeClass('view-ani');
            $(".nav-m").removeClass('on');
        }else{
            $(this).addClass('ok');
            $("#hd").addClass('hd-ani');
            $(".view").addClass('view-ani')
            $(".nav-m").addClass('on');
        }
    });


    $(".loading").fadeOut();
});


if( $('#banner').length > 0 ){
    $(".arrowDown i").click(function(){
        $('body,html').animate({
            scrollTop: $(window).height()
        })
    });


 
 
}else{
    $(".arrowDown i").click(function(){
        $.fn.fullpage.moveSectionDown();
    });

}



$('.go-top a').click(function(){ 
    $('body,html').animate({
        scrollTop: 0
    })
});

// 电话颤抖
function state1(){
    $(".fixed li.tel span").removeClass("r2"); 
    $(".fixed li.tel span").addClass("r1");
    setTimeout(state2,90);
}
function state2(){     
    $(".fixed li.tel span").removeClass("r1");
    $(".fixed li.tel span").addClass("r2"); 
    setTimeout(state1,90);
}
// state1();

$('.fixed').waitForImages().done(function() {
    setTimeout(function(){
        $('.fixed ul').css({opacity:1})
    },300)
});

$('.m-go-top').click(function(event) {
    $('body,html').animate({
        scrollTop: 0
    })
});

var isMobile = {
    Android: function() {
        return navigator.userAgent.match(/Android/i);
    },
    BlackBerry: function() {
        return navigator.userAgent.match(/BlackBerry/i);
    },
    iOS: function() {
        return navigator.userAgent.match(/iPhone|iPad|iPod/i);
    },
    Opera: function() {
        return navigator.userAgent.match(/Opera Mini/i);
    },
    Windows: function() {
        return navigator.userAgent.match(/IEMobile/i) || navigator.userAgent.match(/WPDesktop/i);
    },
    any: function() {
        return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
    }
};

if(isMobile.any()){

    $(window).scroll(function(event) {
         if ( $(window).scrollTop() > 100 ) {
              $('.m-go-top').show();
         }else{
              $('.m-go-top').hide();
         }
     });
}
