jQuery(document).ready(function ($) {
    window.onresize = function (event) {
        var mainHeight = document.getElementById('header-panel').offsetHeight + document.getElementById('main-box').offsetHeight + document.getElementById('footer-panel').offsetHeight;  // document.getElementById('wrapper').offsetHeight;
        var menuHeight = document.getElementById('main-menu-box') === null ? 0 : document.getElementById('header-panel').offsetHeight + document.getElementById('main-menu-box').offsetHeight + document.getElementById('footer-panel').offsetHeight;
        var windowHeight = window.innerHeight;
        var newHeight;
        if (mainHeight <= windowHeight && menuHeight <= windowHeight) {
            newHeight = document.getElementById('main-box').offsetHeight;
            $('#main-panel').css('min-height', newHeight + windowHeight - mainHeight);
        } else if (menuHeight > windowHeight && menuHeight > mainHeight) {
            newHeight = document.getElementById('main-menu-box').offsetHeight;
            $('#main-panel').css('min-height', newHeight);
        } else if (mainHeight > windowHeight && mainHeight > menuHeight) {
            newHeight = document.getElementById('main-box').offsetHeight;
            $('#main-panel').css('min-height', newHeight + windowHeight - mainHeight);
        }
    };

    $(window).resize();
   
});

if (typeof (Storage) !== "undefined" && localStorage !== undefined) {
    // Code for localStorage/sessionStorage.
    if (localStorage.getItem("mainMenuBox") === "true") {

    } else if (localStorage.getItem("mainMenuBox") === "false") {
        var $menuPanel = jQuery('#main-menu-box');
        $menuPanel.hide();
        jQuery('.right-panel').css('margin-left', '0');
    } else {
        localStorage.setItem("mainMenuBox", true);
    }
} else {
    // Sorry! No Web Storage support..
}

jQuery('.nav-menu-right-panel').click(function () {
    jQuery('#main-collapse-btn').trigger('click');

    return false;
});

jQuery('#main-collapse-btn').click(function () {
    var $menuPanel = jQuery('#main-menu-box');
    if (typeof (Storage) !== "undefined" && localStorage !== undefined) {
        localStorage.setItem("mainMenuBox", $menuPanel.is(':hidden'));
    }

    if ($menuPanel.is(':hidden')) {
        jQuery('.right-panel').css('margin-left', '222px');
        $menuPanel.show('fast');
    } else {
        $menuPanel.hide('fast', function () {
            jQuery('.right-panel').css('margin-left', '0');
        });
    }

    return false;
});



jQuery('.menu-directive ul li a').click(function () {
    jQuery(this).parent().siblings().find('a.active').removeClass('active');
    jQuery(this).addClass('active');
    console.log('Bạn vừa click vào menu .menu-directive');

    // return false;
});

/*
jQuery('.menu-topics ul li a').click(function () {
    jQuery(this).parent().siblings().find('a.active').removeClass('active');
    jQuery(this).addClass('active');
    console.log('Bạn vừa click vào menu .menu-topics');

    return false;
});
*/

/*
jQuery('#topics-result ol li a').click(function () {
    jQuery(this).parent().siblings().find('a.active').removeClass('active');
    jQuery(this).addClass('active');
    console.log('Bạn vừa click vào menu #topics-result');

    return false;
});
*/

function hideAllContext() {
    jQuery('#topic-context-menu:visible').hide();
    jQuery('#keywords-context-menu:visible').hide();
}

/*
jQuery('.menu-topics ul li a').bind('contextmenu', function (e) {
    hideAllContext();
    console.log("Bạn vừa chọn: " + jQuery(this).text().trim());
    jQuery('#topic-context-menu').css({ 'top': e.pageY + 'px', 'left': e.pageX + 'px' }).show();

    e.preventDefault();

    return false;
});
*/

/*
jQuery('#topics-result ol li a').bind('contextmenu', function (e) {
    hideAllContext();
    console.log("Bạn vừa chọn: " + jQuery(this).text().trim());
    jQuery('#keywords-context-menu').css({ 'top': e.pageY + 'px', 'left': e.pageX + 'px' }).show();

    e.preventDefault();

    return false;
});
*/

jQuery(document).bind('click keyup contextmenu', function () {
    hideAllContext();
});

jQuery('a').click(function () {
    hideAllContext();

    // return false;
});

/*Test*/
jQuery('#topic-context-menu ul li a, #topics-result ol li a').click(function () {
    console.log("The a(" + jQuery(this).text().trim() + ") vua duoc click");

    return false;
});
/*End Test*/

/* More options: 
http://bootstrap-datepicker.readthedocs.org/en/release/index.html
http://eternicode.github.io/bootstrap-datepicker/?markup=input&format=&weekStart=&startDate=&endDate=&startView=0&minViewMode=0&todayBtn=false&language=en&orientation=auto&multidate=&multidateSeparator=&keyboardNavigation=on&forceParse=on#
*/
jQuery('.ttx-date-picker').datepicker({
    language: 'vi',
    todayHighlight: true,
    todayBtn: 'linked',
    autoclose: true
}).on('show', function () {
    // Make sure that z-index is above any open modal.
    var $input = $(this);
    var modalZIndex = $input.closest('.modal').css('z-index');
    var zIndex = 2000;
    if (modalZIndex) {
        zIndex = parseInt(modalZIndex) + 1;
    }

    jQuery('.datepicker').css("z-index", zIndex);
});

//----------------Edit User click header---------------------------------------------------------------------------
function loadRestUserEdit(userId){
	jQuery.ajax({
		type:'get',
		url: '/ttxvn/manage/restuser/loadUserEdit',
		data: "userId="+userId,
		dataType:'json',
		success: function(response){
			jQuery("#id-restuser-add").val(response.userId);
			jQuery("#rest-username-edit-user").val(response.userName);
			jQuery("#rest-password-edit-user").val(response.userPassword);
			jQuery("#rest-email-edit-user").val(response.userEmail);
			jQuery("#rest-lastname-edit-user").val(response.userLastname);
			jQuery("#rest-middle-edit-user").val(response.userMiddleName);
			jQuery("#rest-firstname-edit-user").val(response.userFirstName);
			jQuery("#rest-birth-date-edit-user").val(response.userBirthOfDate);
			if(response.userSex == "FEMALE")
				{document.getElementById("rest-gender-edit-user-female").checked = true;}
			else{document.getElementById("rest-gender-edit-user-male").checked = true;}
			jQuery("#rest-phone-edit-user").val(response.userPhoneNumber);
			jQuery("#rest-address-edit-user").val(response.userAddress);
			jQuery("#rest-district-edit-user").val(response.userDistrict);
			jQuery("#rest-city-edit-user").val(response.userCity);
			
			jQuery('#edit-restuser-popup').modal();
			
			jQuery("#id-change-password-restuser").val(response.userId);
			jQuery("#username-change-password-restuser").val(response.userName);
			jQuery("#rest-label-username").text(response.userName);
			jQuery("#rest-label-email").text(response.userEmail);

		},
		error: function(response){
			console.log('Error while request..'+response.status+' '+response.statusText);
		}
	});
	

	 jQuery("#edit-restuser-popup form").on('submit', function(){
			var lastName = jQuery("#rest-lastname-edit-user").val();
			var middleName = jQuery("#rest-middle-edit-user").val();
			var firstName = jQuery("#rest-firstname-edit-user").val();
			var birthOfDate = jQuery("#rest-birth-date-edit-user").val();
			var sex;
			if(document.getElementById("rest-gender-edit-user-female").checked == true){
				sex = "FEMALE";
			}else {
				sex = "MALE";
			}
			var phoneNumber = jQuery("#rest-phone-edit-user").val();
			var address = jQuery("#rest-address-edit-user").val();
			var district = jQuery("#rest-district-edit-user").val();
			var city = jQuery("#rest-city-edit-user").val();
			var userId = jQuery("#id-restuser-add").val();
			
			jQuery.ajax({
				type:'post',
				url: '/ttxvn/manage/restuser/updateUser',
					data : {
	 					userId : userId,
						userLastname : lastName,
						userMiddleName : middleName,
						userFirstName : firstName,
						userBirthOfDate : birthOfDate,
						userSex : sex,
						userPhoneNumber : phoneNumber,
						userAddress : address,
						userDistrict : district,
						userCity : city
					},
					dataType : 'json',
					success : function(response) {
						if (response.message != '')
							ttxModal.showError({
								message : response.message,
		 						onHidden: function(e){
									jQuery('body').addClass('modal-open');
								}
							});
						else {
							jQuery('#edit-restuser-popup').modal('hide');
						}
					}
				});
				return false;
			});
}

jQuery("#change-password-restuser-popup form").submit(function(){
	
	var oldPassword = jQuery('#old-password-change-password-restuser').val();
	var newPassword = jQuery('#new-password-change-password-restuser').val();
	var confirmNewPassword = jQuery('#confirm-password-change-password-restuser').val(); 	
	var username = jQuery('#username-change-password-restuser').val();
	var idchangepassword = jQuery('#id-change-password-restuser').val();
	
	jQuery.ajax({
		type:'get',
		url: '/ttxvn/manage/restuser/changePassword',
		data: {
			idchangepassword: idchangepassword,
			username: username,
			oldPassword: oldPassword,
			newPassword: newPassword,
			confirmNewPassword: confirmNewPassword,
		
		},
		
		dataType:'json',
		success: function(response){
			if(response.message!='') {
				ttxModal.showError({message: response.message,
						onHidden: function(e){
							jQuery('body').addClass('modal-open');
						}
				});	
			} else {	
				jQuery('#change-password-restuser-popup').modal('hide');
				jQuery('#edit-restuser-popup').modal('hide');	
				refreshForm();
				}
		}
	});
	
	return false;
});

function clickChange(){
	jQuery('#change-password-restuser-popup').modal('show');
}

function refreshForm()
{
	jQuery("#old-password-change-password-restuser").val('');
	jQuery("#new-password-change-password-restuser").val('');
	jQuery("#confirm-password-change-password-restuser").val('');
}


jQuery('#rest-name-login').on('click', function(){
	var userId = jQuery(this).siblings('input[type=hidden]').val();
	loadRestUserEdit(userId);
	jQuery('#edit-restuser-popup').modal();
})

//------------------------------------------------------------------------------------------------

var $messagePopup = jQuery('#message-popup');
var $messagePopupBody = jQuery('#ttx-message-body');
if ($messagePopup && $messagePopupBody) {
    var messageYesNoCommand = $messagePopup.find('.ttx-yes-no');
    var messageOptionsDefault = {
        message: null,
        yesButton: false,
        yesText: 'Có',
        noButton: false,
        noText: 'Không',
        onHidden: function(event){ /*do something...*/ }
    };

    function setMessageText(message) {
        var $p = jQuery('<p></p>');
        var $b = jQuery('<b></b>');
        $b.html(message);
        $p.html($b);
        $messagePopupBody.html($p);

        return $p;
    }

    function setMessageCommandButton(options) {
        if (options.yesButton || options.noButton) {
            if (options.yesButton) {
                messageYesNoCommand.find('#yes-message-btn').text(options.yesText).show();
            } else {
                messageYesNoCommand.find('#yes-message-btn').hide();
            }

            if (options.noButton) {
                messageYesNoCommand.find('#no-message-btn').text(options.noText).show();
            } else {
                messageYesNoCommand.find('#no-message-btn').hide();
            }

            messageYesNoCommand.show();
        } else {
            messageYesNoCommand.hide();
        }
    }

    window.ttxModal = {
        showInfo: function (options) {
            var configs = jQuery.extend(messageOptionsDefault, options);
            if (configs.message !== null && configs.message.length > 0) {
                setMessageText(configs.message);
                setMessageCommandButton(configs);
                $messagePopup.modal();
            }

            return $messagePopup;
        },

        showError: function (options) {
            var configs = jQuery.extend(messageOptionsDefault, options);
            if (configs.message !== null && configs.message.length > 0) {
                setMessageText(configs.message).addClass('text-danger');
                setMessageCommandButton(configs);
                $messagePopup.modal().on('hidden.bs.modal', function(e){
                	configs.onHidden(e);
                }); //configs.onHidden
            }

            return $messagePopup;
        },

    };
    jQuery('#change-password-restuser-popup').on('hidden.bs.modal',function(e){
    	jQuery('body').addClass('modal-open');
    });
}

