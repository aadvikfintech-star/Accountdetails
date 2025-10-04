$(document).ready(function(){
		
	$(document).on('keyup', '.onlynumberwithdot', function(){
		 var regex = /\d*\.?\d?\d?/g;
		 this.value = regex.exec(this.value);
		 //this.value = this.value.replace(/[^\d.]/g, '');
	});
	$(document).on('change', '.onlynumberwithdot', function(){
		var regex = /\d*\.?\d?\d?/g;
		this.value = regex.exec(this.value);
		//this.value = this.value.replace(/[^\d.]/g, '');
	});
	$(document).on('keyup', '.onlynumber', function(){
		var regex = /\d*\d?\d?/g;
		this.value = regex.exec(this.value);
		//this.value = this.value.replace(/[^\d.]/g, '');
	});
	$(document).on('change', '.onlynumber', function(){
		var regex = /\d*\d?\d?/g;
		this.value = regex.exec(this.value);
		//this.value = this.value.replace(/[^\d.]/g, '');
	});
	
	$(document).on('keyup', '.onlyaphabets', function(){
		var regex = /^[A-Za-z]+/i;
		this.value = regex.exec(this.value);
	});
	$(document).on('change', '.onlyaphabets', function(){
		var regex = /^[A-Za-z]+/i;
		this.value = regex.exec(this.value);
	});

	$(document).on('keyup', '.onlyaphabetswithspace', function(){
		var regex = /^[A-Za-z\s]+/i;
		this.value = regex.exec(this.value);
	});
	$(document).on('change', '.onlyaphabetswithspace', function(){
		var regex = /^[A-Za-z\s]+/i;
		this.value = regex.exec(this.value);
	});

	$(document).on('keyup', '.alphanumeric', function(){
		var regex = /^[a-z0-9]+/i;
		this.value = regex.exec(this.value);
	});
	$(document).on('change', '.alphanumeric', function(){
		var regex = /^[a-z0-9]+/i;
		this.value = regex.exec(this.value);
	});

	$(document).on('keyup', '.latlong', function(){
		 var regex = /\d*\.?\d?\d?\d?\d?\d?\d?/g;
		 this.value = regex.exec(this.value);
		 //this.value = this.value.replace(/[^\d.]/g, '');
	});
	$(document).on('change', '.latlong', function(){
		var regex = /\d*\.?\d?\d?\d?\d?\d?\d?/g;
		this.value = regex.exec(this.value);
		//this.value = this.value.replace(/[^\d.]/g, '');
	});
	
});

var a = ['','One ','Two ','Three ','Four ','Five ','Six ','Seven ','Eight ','Nine ','Ten ','Eleven ','Twelve ','Thirteen ','Fourteen ','Fifteen ','Sixteen ','Seventeen ','Eighteen ','Nineteen '];
var b = ['', '', 'Twenty','Thirty','Forty','Fifty','Sixty','Seventy','Eighty','Ninety'];

function toWords (num) {
    if ((num = num.toString()).length > 9) return 'overflow';
    n = ('000000000' + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
    if (!n) return; var str = '';
    str += (n[1] != 0) ? (a[Number(n[1])] || b[n[1][0]] + ' ' + a[n[1][1]]) + 'Crore ' : '';
    str += (n[2] != 0) ? (a[Number(n[2])] || b[n[2][0]] + ' ' + a[n[2][1]]) + 'Lakh ' : '';
    str += (n[3] != 0) ? (a[Number(n[3])] || b[n[3][0]] + ' ' + a[n[3][1]]) + 'Thousand ' : '';
    str += (n[4] != 0) ? (a[Number(n[4])] || b[n[4][0]] + ' ' + a[n[4][1]]) + 'Hundred ' : '';
    str += (n[5] != 0) ? ((str != '') ? 'and ' : '') + (a[Number(n[5])] || b[n[5][0]] + ' ' + a[n[5][1]]) + '' : '';
    return str+ 'Only';
}
