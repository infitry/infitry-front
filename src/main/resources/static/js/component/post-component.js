Vue.component('post-component', {
	template: '#postTemplate',
	props: ['size'],
	data: function() {
		return {
			list: [],
	  		baseUrl: '/blog/post/axios-list',
	  		categorySeq: $('#blogPostCategorySeq').val(),
	  		page: 0,
	  		last: false,
			first: true
		}	
	},
	filters: {
		dateFormat: function(value) {
		  	if (!value) {
		  		return '';
		  	}
		  	return moment(value).format("YYYY .MM .DD");
		}
	},
	methods: {
		getList: function() {
 			 var vm = this;
 			 axios.get(this.baseUrl, {
 				 params: {
 					pageNumber: this.page,
 					pageSize: this.size,
 					blogPostCategorySeq: this.categorySeq
 				 },
 				 headers: { 
 					 'requestType': 'axios'
 				 }
 			}).then(function(response) {
 	        	vm.list = response.data.content;
 	        	vm.page = response.data.number;
 	        	vm.last = response.data.last;
 	        	vm.first = response.data.first;
 	        	if (!vm.last) {
 	        		$('#pagination').show();
 	        	}
 	        	if (response.data.totalElements == 0) {
 	        		$('#listNull').show();
 	        	} else {
 	        		$('#listNull').hide();
 	        	}
 	        })
 	        .catch(function(e) {
 	        	console.warn('error!!! :' + e);
 	        });	
 		},
 		getNextPage: function() {
 			this.page += 1;
 			this.getList();
 		},
 		getPrevPage: function() {
 			this.page -= 1;
 			this.getList();
 		},
 		readMore: function(blogPostSeq) {
 			var detailUrl = '/blog/post/detail/' + blogPostSeq;
 			if (this.categorySeq) {
 				detailUrl += '?blogPostCategorySeq=' + this.categorySeq;
 			}
 			location.href = detailUrl;
 		}
	},
	created: function() {
		this.getList();
	}
});