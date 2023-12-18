
$(document).ready(function () {
  var rowsPerPage = 20; // Set the number of rows per page
  var table = $('#employeeTable'); // Change this to match your table ID
  var tbody = table.find('tbody');

  var totalRows = tbody.find('tr').length;
  var totalPages = Math.ceil(totalRows / rowsPerPage);

  function showPage(page) {
    tbody.find('tr').hide();
    var startIndex = (page - 1) * rowsPerPage;
    var endIndex = startIndex + rowsPerPage;
    tbody.find('tr').slice(startIndex, endIndex).show();
  }

  showPage(1); // Show the first page by default

  $('.pagination').on('click', 'a.page-link', function (e) {
    e.preventDefault();
    var page = parseInt($(this).text(), 10);
    showPage(page);
  });

  // Previous page button
  $('.pagination').on('click', 'li.page-item.disabled a.page-link', function (e) {
    e.preventDefault();
    var currentPage = parseInt($('.pagination li.active a.page-link').text(), 10);
    if (currentPage > 1) {
      showPage(currentPage - 1);
    }
  });

  // Next page button
  $('.pagination').on('click', 'li.page-item:not(.disabled) a.page-link', function (e) {
    e.preventDefault();
    var currentPage = parseInt($('.pagination li.active a.page-link').text(), 10);
    if (currentPage < totalPages) {
      showPage(currentPage + 1);
    }
  });
});


