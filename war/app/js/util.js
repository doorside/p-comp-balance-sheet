function getParameterByName(name, inputstring) {
    var ips;
    if (inputstring.length == 0) {
        ips = window.location;
    } else {
        ips = inputstring;
	}
    var match = RegExp('[?&]' + name + '=([^&]*)').exec(ips);
    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
}

jQuery(document).bind("mobileinit", function() {
    //jQuery.mobile.page.prototype.options.addBackBtn = true;
});

