function share_init () {
  $('[data-share-network]').on('click', function (e) {
    e.preventDefault();

    var shareNetwork = this.dataset.shareNetwork,
        shareObject = {
          ptitle: this.dataset.shareTitle,
          psummary: this.dataset.shareSummary,
          purl: this.dataset.shareUrl,
          pimg: this.dataset.shareImage,
          psource: this.dataset.shareSource
        };

    helpFunctions.share[shareNetwork](shareObject);

  });
}



var helpFunctions = (function () {

  function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) {
      return parts.pop().split(";").shift()
    } else {
      return "undefined";
    }
  }

  var share = {
    facebook: function(info) {
      url  = 'https://www.facebook.com/sharer/sharer.php?';
      url += info.ptitle ? '&title=' + encodeURIComponent(info.ptitle) : '';
      url += info.psummary ? '&description=' + encodeURIComponent(info.psummary) : '';
      url += info.purl ? '&u=' + encodeURIComponent(info.purl) : '&u='+location.href;
      url += info.pimg ? '&picture=' + encodeURIComponent(info.pimg) : '';
      share.popup(url);
    },
      twitter: function(info) {
      url  = 'http://twitter.com/share?';
      url += info.ptitle ? 'text='      + encodeURIComponent(info.ptitle) : '';
      url += info.purl ? '&url='      + encodeURIComponent(info.purl) : '&url='+location.href;
      url += info.purl ? '&counturl=' + encodeURIComponent(info.purl) : '&counturl='+location.href;
      share.popup(url);
    },
    linkedin: function(info) {
      url  = 'https://www.linkedin.com/shareArticle?mini=true';
      url += info.purl ? '&url=' + encodeURIComponent(info.purl) : '&url='+location.href;
      url += info.ptitle ? '&title=' + encodeURIComponent(info.ptitle) : '';
      url += info.psummary ? '&summary=' + encodeURIComponent(info.psummary) : '';
      url += info.psource ? '&source=' + encodeURIComponent(info.psource) : '';
      share.popup(url);
    },
    popup: function(url) {
      window.open(url,'Hello','toolbar=0,status=0,width=626,height=436');
    }
  };

  return {
    getCookie: getCookie,
    share: share
  }

})();

document.addEventListener("DOMContentLoaded", share_init);
