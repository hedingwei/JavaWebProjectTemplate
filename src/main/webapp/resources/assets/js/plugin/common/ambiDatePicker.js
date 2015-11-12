/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$.fn.ambiDatePicker = function(options) {

    //1. 默认参数
    var settings = $.extend({
        format:"yyyy-mm-dd",
        autoclose:true,
        endDate:gettoday(),
        initialDate:gettoday()
    }, options);

    this.datepicker(settings);

    //获取今天日期
    function gettoday(){
        var d = new Date();
        var vYear = d.getFullYear();
        var vMon = d.getMonth() + 1;
        var vDay = d.getDate();
        if(vMon<10){
            vMon="0"+vMon;
        }
        if(vDay<10){
            vDay="0"+vDay;
        }
        // console.log(vYear+"-"+vMon+"-"+vDay);
        var todayDate=vYear+"-"+vMon+"-"+vDay;
        return todayDate;
    }
    //获取昨天日期
    function getyesterday(){
        var d = new Date();
        var vYear = d.getFullYear();
        var vMon = d.getMonth() + 1;
        var vDay = d.getDate() - 1;
        // console.log(vYear+"-"+vMon+"-"+vDay);
        var yesterdayDate=vYear+"-"+vMon+"-"+vDay;
        return yesterdayDate;
    }

	
	return this;
};



