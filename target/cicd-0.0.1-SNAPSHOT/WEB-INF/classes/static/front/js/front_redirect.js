var url = window.location.protocol + "//" + window.location.host;

function goPrdDetail(prdId, category) {
    window.location.href = url + "/prd/detail?prd_id=" + prdId + "&category=" + category;
}

function goMain() {
    window.location.href = url + "/";
}

function goSearch(searchText) {
    window.location.href = url + "/search?search_text=" + searchText;
}

function goCateogory(category) {
    window.location.href = url + "/category?category=" + category;
}

function goPrdList(category, categoryName, categoryId) {
    window.location.href = url + "/prd/list?category=" + category + "&category_name=" + categoryName + "&category_id=" + categoryId;
}