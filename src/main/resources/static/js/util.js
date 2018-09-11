// 时间戳转格式化后的时间
function timestamp2time(unixTime,isFull, timeZone) {
    unixTime = parseInt(unixTime);
    if (typeof (timeZone) == 'number') {
        unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
    }
    var time = new Date(unixTime);
    var ymdhis = "";
    ymdhis += time.getUTCFullYear() + "-";
    ymdhis += ((time.getUTCMonth() + 1) < 10 ? "0" + (time.getUTCMonth() + 1) : (time.getUTCMonth() + 1)) + "-";
    ymdhis += (time.getUTCDate() < 10 ? "0" + time.getUTCDate() : time.getUTCDate()) + " <br>";
    ymdhis += (time.getHours() < 10 ? "0" + time.getHours() : time.getHours()) + ":";
    ymdhis += (time.getUTCMinutes() < 10 ? "0" + time.getUTCMinutes() : time.getUTCMinutes()) ;
    // ymdhis += (time.getUTCMinutes() < 10 ? "0" + time.getUTCMinutes() : time.getUTCMinutes()) + ":";
    // ymdhis += (time.getUTCSeconds() < 10 ? "0" + time.getUTCSeconds() : time.getUTCSeconds());
    if (isFull === true) {
        ymdhis += (time.getHours() < 10 ? "0" + time.getHours() : time.getHours()) + ":";
        ymdhis += (time.getUTCMinutes() < 10 ? "0" + time.getUTCMinutes() : time.getUTCMinutes()) + ":";
        ymdhis += (time.getUTCSeconds() < 10 ? "0" + time.getUTCSeconds() : time.getUTCSeconds());
    }
    return ymdhis;
}

// 更换显示时间轴上所有的时间戳
function alertTimeaxis(){
    console.log("start alert time");
    $(".movie-timeaxis").each(function(index){
       let text = $(this).text();
       const new_time = timestamp2time(text);
       // console.log("old time is: ",text,"new time is: ",new_time);
       $(this).html(new_time);
       var i_spot = "<i class='spot'></i>";
       $(this).append(i_spot);
    });
}

// add movie 表单点击类型单选按钮后
function choiceTypeRadio(){
   $(":radio").change(function(){
      console.log("your are ",$(this).val());
      let type = $(this).val();
       window.localStorage.setItem("movie-type",type);
      if(type==0){
          $(".tv-need").fadeOut("fast");
          $("input[name='episode']").val("");
          $("input[name='season']").val("");
      }else{
          $(".tv-need").fadeIn("fast");
      }
   });
}

// 记录上次选中的movie类型按钮
function setMovieTypeRadio(){
    var type = window.localStorage.getItem("movie-type");
    if(null==type){
        type=1;
    }else if(type==0){
        // $(".tv-need").fadeOut("slow");
        $(".tv-need").hide();
    }
    $("input:radio").eq(parseInt(type)).prop("checked",true);
}

function popupToast(heading,text,icon){
    $.toast({
        heading: heading,
        text: text,
        showHideTransition: 'slide',
        icon: icon,
        position: 'bottom-right',
    });
}

// 给datetime-local赋默认值
function defaultDateTime(){
    var now = new Date();
    var res = "";
    res += now.getUTCFullYear()+"-";
    res += ((now.getUTCMonth() + 1) < 10 ? "0" + (now.getUTCMonth() + 1) : (now.getUTCMonth() + 1))+"-";
    res += (now.getUTCDate() < 10 ? "0" + now.getUTCDate() : now.getUTCDate()) +"T";
    res += now.getHours()+":";
    res += now.getUTCMinutes()<10? "0"+now.getUTCMinutes() : now.getUTCMinutes();
    console.log(res);
    $("#inputCreateTime").val(res);
}


