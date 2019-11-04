$(function(){
	
	function replaceContent(newHTML) {
		document.open();
		document.write(newHTML);
		document.close();
	}
//	function replaceItems(html) {
//		console.log(html);
//		$('#items').replaceWith($(html))
//	}

	$('button[name="addItem"]').click(function(e){
		e.preventDefault();
		var data = $('form').serialize();
//		console.log("INSIDE ADD ACTION")
//		console.log(data);
//		$.post('/app/multiadd?addItem', data, replaceItems)
		$.post('/app/multiadd?addItem', data, function(response, status) {
			console.log(response);
			console.log(status);
			replaceContent(response);
		})
	})
	
	$('button[name="removeItem"]').click(function(e) {
	    e.preventDefault();
	    var data = $('form').serialize();
	    var idx = $(this).val();
	    //console.log($(this).val())
//	    console.log("INSIDE REMOVE ACTION")
//		console.log(data);
	    $.post('/app/multiadd?removeItem='+idx, data, function(response, status) {
	    	replaceContent(response);
	    });
	});
})
