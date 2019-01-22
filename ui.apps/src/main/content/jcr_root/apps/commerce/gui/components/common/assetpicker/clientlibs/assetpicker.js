/*
 * ADOBE CONFIDENTIAL
 *
 * Copyright 2014 Adobe Systems Incorporated
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 */

(function(window, document, Granite, $) {
    "use strict";

    var rel = ".cq-AssetPickerField";

    var $assetPicker;

    var modifiedPreviewTargets = [];

    /**
     * Run the AssetPicker dialog.
     */
    function showAssetPicker() {
        var href = Granite.HTTP.externalize("/aem/assetpicker?mode=single"),
            $iframe = $('<iframe class="cq-AssetPicker cq-AssetPicker-iframe"></iframe>'),
            $modal = $('<div class="cq-AssetPicker cq-AssetPicker-modal coral-Modal"></div>');

        $iframe.attr("src", href).appendTo($modal);

        $modal.appendTo("body").modal({
            type: "default",
            buttons: [],
            visible: true
        });
        return $modal;
    }

    /**
     * Receive two-part messages from the AssetPicker dialog.  The "data" part indicates the
     * template picker path should be updated; the "config" part indicates whether or not the
     * dialog should be closed.
     */
    function receiveMessage(event) {
        if (event.origin !== location.origin) {
            return;
        }
        if (!$assetPicker) {
            return;
        }

        var fromDam = JSON.parse(event.data);

        if (fromDam.data) {
            var $sink = $assetPicker.data("sink"),
                path = fromDam.data[0].path;

            $sink.val(path).change();
        }

        if (fromDam.config) {
            var configFromDam = fromDam.config;
            if (configFromDam.action === 'close' || configFromDam.action === 'done') {
                $assetPicker.data("modal").hide();
            }
        }
    }

    window.addEventListener("message", receiveMessage, false);

    /*
     * Update the previewTarget in response to js-datafield changes.
     */
    $(document).on("change", rel + " .js-datafield", function (e) {
        var $pickerField = $(e.target).closest(rel),
            $productImageForm = $pickerField.closest(".product-image"),
            path = $(e.target).val(),
            previewSelector = $pickerField.attr("data-previewtarget"),
            $previewTarget = null;

        if ($productImageForm.length > 0) {
            // The product image form supports multiple asset types and we therefore need to fetch
            // a new (asset-type-specific) UI from the server.  Since this also entails updating
            // the value on the server (so that it knows what UI to return), there's no point in
            // pushing to the modifiedPreviewTargets stack since a reset won't be possible.
            $previewTarget = $productImageForm.find(previewSelector);
            $previewTarget.attr("src", path);

            var resourcePath = $productImageForm.data("resourcePath"),
                productPath = $productImageForm.data("productPath"),
                productAssetPath = $productImageForm.data("productImagePath"),
                postUrl = resourcePath + ".update.html",
                postParams = {
                    'productAssetPath': productAssetPath,
                    'assetReference': path
                };

            $.post(postUrl, postParams, function(html) {
                var getUrl = resourcePath + ".html" + productPath,
                    getParams = {
                        'productPath': productPath,
                        'imagePath': productAssetPath
                    };
                $.get(getUrl, getParams, function(html) {
                    $productImageForm.replaceWith($(html));
                    // instantiate the inserted UI elements
                    $(".product-image").trigger("foundation-contentloaded");
                });
            });
        } else {
            $previewTarget = $(document).find(previewSelector);
            if ($previewTarget.length > 0) {
                if (!$previewTarget.data("reset-value")) {
                    $previewTarget.data("reset-value", $previewTarget.attr("src"));
                    modifiedPreviewTargets.push($previewTarget);
                }
                $previewTarget.attr("src", path);
            }
        }
    });

    /*
     * Reset any modified previewTargets when form is reset.
     */
    $(document).on("reset", "form", function() {
        for (var i = 0; i < modifiedPreviewTargets.length; i++) {
            var $previewTarget = modifiedPreviewTargets[i];
            if ($previewTarget && $previewTarget.data("reset-value")) {
                $previewTarget.attr("src", $previewTarget.data("reset-value"));
            }
        }
    });

    /*
     * Run the AssetPicker dialog when the browse-activator is clicked.
     */
    $(document).on("click", rel + " .js-browse-activator", function (e) {
        var $pickerField = $(e.target).closest(rel),
            $sink = $pickerField.find(".js-datafield");
        $assetPicker = showAssetPicker();
        $assetPicker.data("sink", $sink);
    });

})(window, document, Granite, Granite.$);
