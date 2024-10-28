// 懒人图库 搜集整理 www.lanrentuku.com
$(function(){
	var tophtml="<div id=\"izl_rmenu\" class=\"izl-rmenu\"><a href=\"tencent://Message/?Uin=123456789&websiteName= =&Menu=yes\" class=\"btn btn-qq\"></a><div class=\"btn btn-wx\"><img class=\"pic\" src=\"images/weixin.jpg\" onclick=\"window.location.href=\' \'\"/></div><div class=\"btn btn-dianhua\"><div class=\"dianhua\">0538-6395677</div></div><div class=\"btn btn-top\"></div></div>";
	$("#top").html(tophtml);
	$("#izl_rmenu").each(function(){
		$(this).find(".btn-wx").mouseenter(function(){
			$(this).find(".pic").fadeIn("fast");
		});
		$(this).find(".btn-wx").mouseleave(function(){
			$(this).find(".pic").fadeOut("fast");
		});
		$(this).find(".btn-dianhua").mouseenter(function(){
			$(this).find(".dianhua").fadeIn("fast");
		});
		$(this).find(".btn-dianhua").mouseleave(function(){
			$(this).find(".dianhua").fadeOut("fast");
		});
		$(this).find(".btn-top").click(function(){
			$("html, body").animate({
				"scroll-top":0
			},"fast");
		});
	});
	var lastRmenuStatus=false;
	$(window).scroll(function(){//bug
		var _top=$(window).scrollTop();
		if(_top>200){
			$("#izl_rmenu").data("expanded",true);
		}else{
			$("#izl_rmenu").data("expanded",false);
		}
		if($("#izl_rmenu").data("expanded")!=lastRmenuStatus){
			lastRmenuStatus=$("#izl_rmenu").data("expanded");
			if(lastRmenuStatus){
				$("#izl_rmenu .btn-top").slideDown();
			}else{
				$("#izl_rmenu .btn-top").slideUp();
			}
		}
	});
});