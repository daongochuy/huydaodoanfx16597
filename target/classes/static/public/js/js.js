$(function() {
 
	if($(document.body).width() < 768) {
        $(".mo-header-menu").click(function(){
			$(".mo-leftmenu").toggleClass("menu-transitioning");
		});
		$(".mo-leftmenu .tit span").click(function(){
			$(".mo-leftmenu").removeClass("menu-transitioning");
		});
		$(".mo-header-search").click(function(){
			$(".mo-search").toggle();
		});
		$(".down-btn").on('click',function(){
			$(this).parent('li').toggleClass("active");
			$(this).siblings('ul').slideToggle();
			return false;
		});
		$('.sidemenu h3').click(function(){
			if( $(this).siblings('ul').is(':hidden') ) {
				$(this).addClass('active').siblings('ul').slideDown(); 
			} else{
				$(this).removeClass('active').siblings('ul').slideUp();
			}
		});
		$('.left_nav h3').click(function(){
			if( $(this).siblings('ul').is(':hidden') ) {
				$(this).addClass('active').siblings('ul').slideDown(); 
			} else{
				$(this).removeClass('active').siblings('ul').slideUp();
			}
		});
	};
  
$(window).scroll(function() {
		if( $(window).width() > 768 ) {
			if($(this).scrollTop() >=200) {
				$('#gotop').fadeIn();	
			} else {
				$('#gotop').fadeOut();
			}
		}
	});
	$('#gotop,.gotop').click(function(){
	   $('html, body').animate({scrollTop:0}, 'slow');
	});
	$('.gobot').click(function(){
		var win = $(window);
		var win_h = win.height();
		var scroll_t = win.scrollTop();
		$("body,html").animate({
			"scrollTop": scroll_t + win_h
		});
	});
    $('.sidemenu ul li').click(function(){
	    if( $("dl",this).is(':hidden') ) {
		    $(this).addClass('active').find("dl").slideDown(); 
	    } else{
			$(this).removeClass('active').find("dl").slideUp();
		}
    });
    $body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body');
    $('.book').click(function() {$body.animate({scrollTop: $('.inquirShow').offset().top-70},'slow')});
});
// Contact
$(".c_sub").click(function(){	if($(".c_name").val()==''||$(".c_email").val()==''||$(".c_country").val()==''||$(".c_cnt").val()==''||$(".c_qucode").val()==''){
		alert("Please fill in the fields marked with (*)!");
		return false;
	}
	if (!(/^[0-9a-zA-Z|\-|\.|\_]{2,50}[@]{1}[0-9a-zA-Z|\-]{2,50}[\.]{1}[0-9a-zA-Z|\-\.]{2,50}$/.test($(".c_email").val()))){
		alert('The E-mail format is incorrect!');	
		return false
	}
});
$(".c_sub").click(function(){
	if($(".c_name").val()==''||$(".c_email").val()==''||$(".c_cnt").val()==''){
		alert("Please fill in the fields marked with (*)!");
		return false;
	}
	if (!(/^[0-9a-zA-Z|\-|\.|\_]{2,50}[@]{1}[0-9a-zA-Z|\-]{2,50}[\.]{1}[0-9a-zA-Z|\-\.]{2,50}$/.test($(".c_email").val()))){

		alert('The E-mail format is incorrect!');	

		return false

	}

});
