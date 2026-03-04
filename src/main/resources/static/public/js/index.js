$(function() {
	var banner= new Swiper('#banner .swiper-container', {
        pagination: '#banner .swiper-pagination',
        paginationClickable: true,
		loop: true,
		autoplay: 4000
    });
	var mySwiper1 = new Swiper ('.indexA .swiper-container', {
		loop: true,
		autoplay: 3000,
		autoplayDisableOnInteraction:false,
		slidesPerView:4,
		spaceBetween: 10,
		nextButton: '.indexA .swiper-button-next',
		prevButton: '.indexA .swiper-button-prev',
		breakpoints: {
			768: {
				slidesPerView:2,
				spaceBetween:10
			}
		}
	});
	var mySwiper2= new Swiper('.indexD .swiper-container', {
		autoplay: 3000,
		nextButton: '.indexD .swiper-button-next',
		prevButton: '.indexD .swiper-button-prev',
		parallax:true,
		onSlideChangeStart: function(swiper){
			$('.indexD .swiper-button-list .swiper-button-num').html("<b>"+(swiper.realIndex+1)+"</b> / "+swiper.slides.length);
		    //alert(swiper.activeIndex);
		}
	});
	$('.brand-owl').owlCarousel({
	    nav: true,
	    margin: 20,
	    navText: ["<i class='fa fa-angle-left' aria-hidden='true'></i>", "<i class='fa fa-angle-right' aria-hidden='true'></i>"],
	    slideSpeed: 600,
	    paginationSpeed: 400,
	    singleItem: true,
	    pagination: false,
	    dots: false,
	    autoplay: true,
	    autoplayTimeout: 4500,
	    autoplayHoverPause: false,
	    autoHeight: false,
	    loop: true,
	    responsive: {
	        0: {
	            items: 3
	        },
	        543: {
	            items: 3
	        },
	        768: {
	            items: 3
	        },
	        991: {
	            items: 4
	        },
	        992: {
	            items: 5
	        },
	        1300: {
	            items: 6
	        },
	        1590: {
	            items: 6
	        }
	    }
	});

	

	$(".listn-blogs").owlCarousel({
	    nav: true,
	    slideSpeed: 600,
	    paginationSpeed: 400,
	    singleItem: true,
	    pagination: false,
	    dots: false,
	    autoplay: true,
	    lazyLoad: true,
	    navText: ["<i class='fa fa-angle-left' aria-hidden='true'></i>", "<i class='fa fa-angle-right' aria-hidden='true'></i>"],
	    margin: 20,
	    autoplayTimeout: 4000,
	    autoplayHoverPause: true,
	    autoHeight: false,
	    loop: true,
	    responsive: {
	        0: {
	            items: 2

	        },
	        543: {
	            items: 2

	        },
	        768: {
	            items: 2

	        },
	        991: {
	            items: 3
	        },
	        992: {
	            items: 3
	        },
	        1024: {
	            items: 4
	        },
	        1200: {
	            items: 5
	        },
	        1590: {
	            items: 5
	        }
	    }
	});


	$(".ykkh").owlCarousel({
	    nav: true,
	    slideSpeed: 600,
	    paginationSpeed: 400,
	    singleItem: true,
	    pagination: false,
	    dots: false,
	    autoplay: true,
	    lazyLoad: true,
	    navText: ["<i class='fa fa-angle-left' aria-hidden='true'></i>", "<i class='fa fa-angle-right' aria-hidden='true'></i>"],
	    margin: 20,
	    autoplayTimeout: 4000,
	    autoplayHoverPause: true,
	    autoHeight: false,
	    loop: true,
	    responsive: {
	        0: {
	            items: 1

	        },
	        543: {
	            items: 1

	        },
	        768: {
	            items: 2

	        },
	        991: {
	            items: 3
	        },
	        992: {
	            items: 3
	        },
	        1024: {
	            items:3
	        },
	        1200: {
	            items: 3
	        },
	        1590: {
	            items: 3
	        }
	    }
	});
	
	$(".new-block").owlCarousel({
	    nav: true,
	    slideSpeed: 600,
	    paginationSpeed: 400,
	    singleItem: true,
	    pagination: false,
	    dots: false,
	    autoplay: true,
	    lazyLoad: true,
	    navText: ["<i class='fa fa-angle-left' aria-hidden='true'></i>", "<i class='fa fa-angle-right' aria-hidden='true'></i>"],
	    margin: 20,
	    autoplayTimeout: 4000,
	    autoplayHoverPause: true,
	    autoHeight: false,
	    loop: true,
	    responsive: {
	        0: {
	            items: 2

	        },
	        543: {
	            items: 2

	        },
	        768: {
	            items: 2

	        },
	        991: {
	            items: 3
	        },
	        992: {
	            items: 3
	        },
	        1024: {
	            items: 4
	        },
	        1200: {
	            items: 4
	        },
	        1590: {
	            items: 5
	        }
	    }
	});
	$(".cmtr").on('click', function () {
	    var id_content = $(this).attr("id_content");
	    var id_comment = $(this).attr("id_comment");
	    $("#id_comment").val(id_comment);
	    $("#id_content").val(id_content);
	    $(this).parents(".comment-body").append($(".frmcomment"));
	    $(".frmcomment").slideDown();
	})
	if(getCookie("dhoten")!="")
	    $("#fullname").val(getCookie("dhoten"));
	if(getCookie("demail")!="")
	    $("#email_contact").val(getCookie("demail"));
	$("#send").on('click', function () {
	    var name = $("#fullname").val();
	    var id = $("#id_sanpham").val();
	    var email = $("#email_contact").val();
	    var content = $("#contentRating").val();
	    setCookie("dhoten",name,30);
	    setCookie("demail",email,30);
	    var userid = "-1";
	    var username = "";
	    var ipar = "-1";
	    var rate = $("#voterating").val();
	    if (name.length == 0) {
	        $(".commentmess").html("<span style='color:#f00'>Xin mời nhập họ tên</span>");
	        return;
	    }
	    if (email.length == 0) {
	        $(".commentmess").html("<span style='color:#f00'>Xin mời nhập Số điện thoại</span>");
	        return;
	    }
	    if (content.length == 0) {
	        $(".commentmess").html("<span style='color:#f00'>Xin mời nhập nội dung</span>");
	        return;
	    }
	    $.ajax({
	        type: "POST",
	        url: "/webservices/srv.asmx/SendRate",
	        data: "{ ipar: '" + ipar + "', id: '" + id + "',userid: '" + userid + "',username: '" + username + "',name: '" + name + "',email: '" + email + "',content: '" + content + "',rate: '" + rate + "'}",
	        contentType: "application/json; charset=utf-8",
	        dataType: "json",
	        success: function (data) {

	            if (data.d == "1") {
	                $(".commentmess").html("<span style='color:#06f'>Nhận xét đã được gửi thành công</span>");
	                $("#fullname").val("");
	                $("#email_contact").val("");
	                $("#contentRating").val("");
	            }
	        },
	        error: function (data) {
	        }
	    });
	});
	$("#btnsend").on('click', function () {
	    var name = $("#cfullname").val();
	    var id = $("#id_content").val();
	    var email = $("#cemail_contact").val();
	    var content = $("#ccontentRating").val();
	    var userid = "-1";
	    var username = "";
	    var ipar = $("#id_comment").val();
	    var rate = "0";
	    if (name.length == 0) {
	        $(".commentmess1").html("<span style='color:#f00'>Xin mời nhập họ tên</span>");
	        return;
	    }
	    if (email.length == 0) {
	        $(".commentmess1").html("<span style='color:#f00'>Xin mời nhập Số điện thoại</span>");
	        return;
	    }
	    if (content.length == 0) {
	        $(".commentmess1").html("<span style='color:#f00'>Xin mời nhập nội dung</span>");
	        return;
	    }
	    $.ajax({
	        type: "POST",
	        url: "/webservices/srv.asmx/SendRate",
	        data: "{ ipar: '" + ipar + "', id: '" + id + "',userid: '" + userid + "',username: '" + username + "',name: '" + name + "',email: '" + email + "',content: '" + content + "',rate: '" + rate + "'}",
	        contentType: "application/json; charset=utf-8",
	        dataType: "json",
	        success: function (data) {

	            if (data.d == "1") {
	                $(".commentmess1").html("<span style='color:#06f'>Nhận xét đã được gửi thành công</span>");
	                $("#fullname").val("");
	                $("#email_contact").val("");
	                $("#contentRating").val("");
	            }
	        },
	        error: function (data) {
	        }
	    });
	});

	$(".bgae").height($(".bga").height() + 320);
	$(".bgae1").height($(".bga3").height() + 320);
	$(".bgae2").height($(".bga11").height() + 300);
	
	$(".prd-block").owlCarousel({
	    nav: true,
	    slideSpeed: 600,
	    paginationSpeed: 400,
	    singleItem: true,
	    pagination: false,
	    dots: false,
	    autoplay: true,
	    lazyLoad: true,
	    navText: ["<i class='fa fa-angle-left' aria-hidden='true'></i>", "<i class='fa fa-angle-right' aria-hidden='true'></i>"],
	    margin: 20,
	    autoplayTimeout: 4000,
	    autoplayHoverPause: true,
	    autoHeight: false,
	    loop: true,
	    responsive: {
	        0: {
	            items: 2

	        },
	        543: {
	            items: 2

	        },
	        768: {
	            items: 2

	        },
	        991: {
	            items: 3
	        },
	        992: {
	            items: 3
	        },
	        1024: {
	            items: 4
	        },
	        1200: {
	            items: 4
	        },
	        1590: {
	            items: 5
	        }
	    }
	});
	$('.indexC .list .tabs ul li').hover(function(){   
       $('.indexC .list .tabs ul li').removeClass('active');
       $(this).addClass('active');
       $('.indexC .list .tabs-list ul').hide();
       $('.indexC .list .tabs-list ul:eq(' + $('.indexC .list .tabs ul li').index(this) + ')').show();
    });
});
function setCookie(cname, cvalue, exdays) {
  const d = new Date();
 
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    let expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
    }
if (c.indexOf(name) == 0) {
    return c.substring(name.length, c.length);
}
}
return "";
}