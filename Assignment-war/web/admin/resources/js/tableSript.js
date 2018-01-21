/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showDetails(){
    
    var table = $('#transactionTable').DataTable({
    });


    $('#transactionTable tbody').on('dblclick', 'tr', function () {
        var tableData = $(this).children("td").map(function () {
            return $(this).text();
        }).get();

        alert("Your data is: " + $.trim(tableData[0]) + " , " + $.trim(tableData[1]) + " , " + $.trim(tableData[2])+ " , " + $.trim(tableData[3]));

    });
}
