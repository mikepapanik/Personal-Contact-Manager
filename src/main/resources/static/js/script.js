const toggleSideBar = () => {
	if ($(".sidebar").is(":visible")) {
		//close sidebar
		$(".sidebar").css("display", "none");
		$(".content").css("margin-left", "0%");
		$(".content").css("width", "100%");
	} else {
		//open sidebar
		$(".sidebar").css("display", "block");
		$(".content").css("margin-left", "23%");
	}
}

const search = () => {
	let query = $("#search-input").val();
	if (query === '') {
		$(".search-result").hide();
	} else {
		let url = `http://localhost:8080/search/${query}`;
		fetch(url).then((response) => response.json())
			.then((data) => {
				let text = `<div class='list-group'>`;
				data.forEach(contact => {
					text += `<a href='/user/${contact.cid}/contact' class='list-group-item list-group-action'>${contact.name}</a>`;
				});
				text += `</div>`;
				$(".search-result").html(text);
				$(".search-result").show();
			});
	}
}

const searchForUser = () => {
	let query = $("#search-input").val();
	if (query === '') {
		$(".search-result").hide();
	} else {
		let url = `http://localhost:8080/search-user/${query}`;
		fetch(url).then((response) => response.json())
			.then((data) => {
				let text = `<div class='list-group'>`;
				data.forEach(user => {
					text += `<a href='/admin/user-profile/${user.id}' class='list-group-item list-group-action'>${user.name}</a>`;
				});
				text += `</div>`;
				$(".search-result").html(text);
				$(".search-result").show();
			});
	}
}

function deleteUser(id) {
	swal({
		title: "Are you sure admin?",
		text: "Once deleted, you will not be able to recover this user!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = '/admin/user-delete/' + id;
			} else {
				swal("Your user is safe!");
			}
		});
}

// Function to sort table
function sortTable() {
	const table = document.getElementById("userTable").getElementsByTagName('tbody')[0];
	const rows = Array.from(table.getElementsByTagName('tr'));

	const adminRow = rows.find(row => row.querySelector('td:nth-child(2)').textContent === 'Administrator');
	const otherRows = rows.filter(row => row.querySelector('td:nth-child(2)').textContent !== 'Administrator');

	otherRows.sort((a, b) => {
		const nameA = a.querySelector('td:nth-child(2)').textContent.toUpperCase();
		const nameB = b.querySelector('td:nth-child(2)').textContent.toUpperCase();
		return nameA.localeCompare(nameB);
	});

	// Clear the table
	table.innerHTML = "";

	// Add the sorted rows back to the table
	if (adminRow) {
		table.appendChild(adminRow);
	}
	otherRows.forEach(row => table.appendChild(row));
}

document.addEventListener('DOMContentLoaded', function () {
	sortTable();
});


