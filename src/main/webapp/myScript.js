$(
	function(){
		
		function sendLogoutRequest(){
			$.ajax(
				{
					url: "LogoutServlet",
					type: "GET",
					success: function(response){
						console.log("logout success ");
						window.location.href = "index.jsp";
					},
					error: function(xhr, status, error){
						console.error("Error: " + error);
					}
				}
			)
		}
		
		
		window.addEventListener("pageshow", function(event){
			if(event.persisted){
				sendLogoutRequest();
			}
		});
		
		setTimeout(function(){
			$("#login-container").fadeIn();
		}, 2500);
	
	}	
)