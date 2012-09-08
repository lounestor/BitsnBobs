$(document).ready(function () {

                $(".inline").colorbox({
                    inline: true,
                    width: "50%"
                });
                $(".callbacks").colorbox({
                    onOpen: function () {
                        alert('onOpen: colorbox is about to open');
                    },
                    onLoad: function () {
                        alert('onLoad: colorbox has started to load the targeted content');
                    },
                    onComplete: function () {
                        alert('onComplete: colorbox has displayed the loaded content');
                    },
                    onCleanup: function () {
                        alert('onCleanup: colorbox has begun the close process');
                    },
                    onClosed: function () {
                        alert('onClosed: colorbox has completely closed');
                    }
                });

                //Example of preserving a JavaScript event for inline calls.
                $("#click").click(function () {
                    $('#click').css({
                        "background-color": "#f00",
                        "color": "#fff",
                        "cursor": "inherit"
                    }).text("Open this window again and this message will still be here.");
                    return false;
                });
            });