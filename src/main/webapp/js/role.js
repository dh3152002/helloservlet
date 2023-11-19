$(document).ready(function() {
	$(".btn-delete").click(function() {
		let id = $(this).attr("id-role");
		let This=$(this);

		// Gọi đường dẫn và lấy dữ liệu từ đường dẫn đó trả ra
		$.ajax({
			method: "DELETE",
			url: `http://localhost:8081/helloservlet/api/role?id=${id}`,
/*			data: { name: "John", location: "Boston" } //Chỉ dành cho phương thức post
*/		})
			.done(function(result) {
				if(result.data){
					This.closest("tr").remove();
				}
				console.log(result);
			});
	})
})