var btn = document.querySelector('.toggle');
var btnst = true;
btn.onclick = function() {
  if(btnst == true) {
    document.querySelector('.toggle span').classList.add('toggle');
    document.getElementById('sidebar').classList.add('sidebarshow');
    btnst = false;
  }else if(btnst == false) {
    document.querySelector('.toggle span').classList.remove('toggle');
    document.getElementById('sidebar').classList.remove('sidebarshow');
    btnst = true;
  }
  }
function logout() {
            window.location.href = 'LogoutServlet';
}

function toPass() {
            window.location.href = 'changePass.jsp';
}

function toName() {
            window.location.href = 'changeName.jsp';
}

function deleteA() {
var confirmation = confirm("Are you sure you want to delete your account?");
    if (confirmation) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "DeleteAccountServlet", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // Success - account deleted
                    window.location.href = "signup.jsp"; // Redirect to signup page
                } else {
                    // Error - handle accordingly
                    console.error("Error deleting account:", xhr.responseText);
                    alert("Error deleting account. Please try again later.");
                }
            }
        };
        xhr.send();
    }
}
        

