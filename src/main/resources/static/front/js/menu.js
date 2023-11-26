/*
Theme Name: 
Version: 
Author: 
Author URI: 
Description: 
*/
/*	IE 10 Fix*/

(function ($) {
	'use strict';
	
	jQuery(document).ready(function () {

		// menu js start
		$.fn.menumaker = function (options) {
	        var flexmenu = $(this), settings = $.extend({
	                format: 'dropdown',
	                sticky: false
	            }, options);
	        return this.each(function () {
	            $(this).find('.mobile-btn').on('click', function () {
	                $(this).toggleClass('menu-opened');
	                var mainmenu = $(this).next('ul');
	                if (mainmenu.hasClass('open')) {
	                    mainmenu.slideToggle().removeClass('open');
	                } else {
	                    mainmenu.slideToggle().addClass('open');
	                    if (settings.format === 'dropdown') {
	                        mainmenu.find('ul').show();
	                    }
	                }
	            });
	            flexmenu.find('li ul').parent().addClass('menu-item-has-children');
	            var subToggle;
	            subToggle = function () {
	                flexmenu.find('.menu-item-has-children').prepend('<span class="submenu-button"></span>');
	                flexmenu.find('.menu-item-has-children>a').append('<i class="ion-ios-arrow-down"></i>');
	                flexmenu.find('.submenu-button').on('click', function () {
	                    $(this).toggleClass('submenu-opened');
	                    if ($(this).siblings('ul').hasClass('open')) {
	                        $(this).siblings('ul').removeClass('open').slideToggle();
	                    } else {
	                        $(this).siblings('ul').addClass('open').slideToggle();
	                    }
	                });
	            };
	            if (settings.format === 'multitoggle')
	                subToggle();
	            else
	                flexmenu.addClass('dropdown');
	            if (settings.sticky === true)
	                flexmenu.css('position', 'fixed');
	            var resizeFix;
	            resizeFix = function () {
	                var mediasize = 974;
	                if ($(window).width() > mediasize) {
	                    flexmenu.find('ul').show();
	                }
	                if ($(window).width() <= mediasize) {
	                    flexmenu.find('ul').hide().removeClass('open');
	                }
	            };
	            resizeFix();
	            return $(window).on('resize', resizeFix);
	        });
	    };

	    $('#flexmenu').menumaker({ format: 'multitoggle' });
	    // menu js end

	    
 	});
	
})(jQuery);