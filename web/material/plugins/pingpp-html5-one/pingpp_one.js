!function () {
    var c, d, a = "http://one.pingxx.com/one_html5/index.js", b = "http://one.pingxx.com/lib/sea.js";
    void 0 === window.seajs && (c = document.createElement("script"), c.src = b, c.type = "text/javascript", document.body.appendChild(c), c.onload = function () {
        seajs.use(a, function (a) {
            window.pingpp_one = a("./init")
        })
    }), d = function () {
    }, d.prototype.init = function (a, b) {
        var a = a, b = b;
        pingpp_one.init(a, b)
    }, d.prototype.success = function (c, d) {
        function e() {
            seajs.use(a, function (a) {
                var b = a("./success");
                b.init(c, d)
            })
        }

        var f;
        c = c, d = d, void 0 === window.seajs ? (f = document.createElement("script"), f.src = b, f.type = "text/javascript", document.body.appendChild(f), f.onload = function () {
            e()
        }) : e()
    }, window.pingpp_one = new d
}();