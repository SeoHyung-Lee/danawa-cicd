var url = window.location.protocol + "//" + window.location.host + "/admin";

    function goLogin() {
        window.location.href = url + "/login";
    }

	function goPrdList() {
		window.location.href = url + "/prd/list?page=0&size=15&cate_one=all&cate_two=all&cate_name=all";
	}

	function goPrdAdd() {
		window.location.href = url + "/prd/add";
	}

	function goCateEdit() {
		window.location.href = url + "/cate/edit";
	}

	function goShowMainSiteEdit() {
	    window.location.href = url + "/showmainsite/edit";
	}