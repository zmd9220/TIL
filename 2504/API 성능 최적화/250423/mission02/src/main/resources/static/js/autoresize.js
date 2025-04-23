function resizeIframe() {
  const iframe = document.getElementById('myFrame');
  try {
    const iframeDoc = iframe.contentWindow.document;
    iframe.style.height = iframeDoc.body.scrollHeight +50+ 'px';
  } catch (e) {
    console.error("다른 출처의 콘텐츠는 접근할 수 없습니다.", e);
  }
}
function changePage(url) {
  const iframe = document.getElementById('myFrame');
  iframe.src = url;
}