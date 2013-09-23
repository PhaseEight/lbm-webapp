$(function() {
	$('#j_password')
			.keypress(
					function(e) {
						var s = String.fromCharCode(e.which);

						if ((s.toUpperCase() === s && s.toLowerCase() !== s && !e.shiftKey)
								|| (s.toUpperCase() !== s
										&& s.toLowerCase() === s && e.shiftKey)) {
							$(this).tipsy('hide');
							$(this)[0].setAttribute('original-title',
									'Warning: Caps Lock is on');
							$(this).tipsy('show');
						} else {
							$(this).tipsy('hide');
							$(this)[0].setAttribute('original-title',
									'Your Password is Case Sensitive.');
							$(this).tipsy('show');
						}
					});
});

