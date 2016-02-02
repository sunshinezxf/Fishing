function hidedistrict() {
    $(".region-district").css("left", "100%");
}

function showdistrict() {
    showcity();
    $(".region-district").css("left", "66.66%");
}

function hidecity() {
    hidedistrict();
    $(".region-city").css("left", "100%");
}

function showcity() {
    $(".region-city").css("left", "33.33%");
}

function hideregioneject() {
    hidecity();
    if ($(".region-eject").hasClass("display")) {
        $(".region-eject").removeClass("display");
        $("#region-prompt").attr("class", "glyphicon glyphicon-chevron-down small");
    }
}

function hidepondeject() {
    if ($(".pond-eject").hasClass("display")) {
        $(".pond-eject").removeClass("display");
        $("#pond-prompt").attr("class", "glyphicon glyphicon-chevron-down small");
    }
}

function hidefisheject() {
    if ($(".fish-eject").hasClass("display")) {
        $(".fish-eject").removeClass("display");
        $("#fish-prompt").attr("class", "glyphicon glyphicon-chevron-down small");
    }
}