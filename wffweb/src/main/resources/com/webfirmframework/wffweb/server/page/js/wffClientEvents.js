/**
 * 
 */

// onload is not good as it will wait for the complete images be downloaded
// this event will be triggered as soon as the dom tree creation is finished
document.addEventListener("DOMContentLoaded",
		function(event) {
			console.log('DOMContentLoaded');
			wffWS.openSocket(wffGlobal.WS_URL);
			MutationObserver = window.MutationObserver
					|| window.WebKitMutationObserver;

			var attrObserver = new MutationObserver(function(mutations,
					observer) {
				// fired when a mutation occurs
				console.log('MutationObserver', mutations, observer);
				var attrNameValues = {};

				for (var i = 0; i < mutations.length; i++) {
					var mutation = mutations[i];
					attrNameValues[mutation.attributeName] = mutation.target
							.getAttribute(mutation.attributeName);
				}

				console.log('attrNameValues', attrNameValues);

			});

			// define what element should be observed by the observer
			// and what types of mutations trigger the callback
			attrObserver.observe(document, {
				// childList: true,
				// characterData: false,
				// should be true otherwise will not invoke function(mutations,
				// observer)
				subtree : true,
				attributes : true
			});

			var tagObserver = new MutationObserver(
					function(mutations, observer) {
						// fired when a mutation occurs
						console.log('tagObserver MutationObserver', mutations,
								observer);
						for (var i = 0; i < mutations.length; i++) {
							var mutation = mutations[i];
							var addedNodes = mutation.addedNodes;
							for (var j = 0; j < addedNodes.length; j++) {
								var addedNode = addedNodes[j];
								console.log('addedNode', addedNode);
							}
						}

					});

			tagObserver.observe(document, {
				childList : true,
				// characterData: false,
				// should be true otherwise will not invoke function(mutations,
				// observer)
				subtree : true
			});

		});
