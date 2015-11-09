/**
 * Created by sunshine on 15/10/5.
 */
function useroperation(articleid, wgateid, operation, status) {
    var url = "http://www.njuat.com/analyse/operate";
    $.post(url, {articleId: articleid, wgateid: wgateid, operation: operation, status: status});
}
