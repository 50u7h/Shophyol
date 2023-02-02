$(document).ready(function() {
	$("a[name='linkRemoveDetail']").each(function(index) {
		$(this).click(function() {
			removeDetailSectionByIndex(index);
		});
	});

});

function addNextDetailSection() {
	allDivDetails = $("[id^='divDetail']");
	divDetailsCount = allDivDetails.length;

	htmlDetailSection = `
		<div class="row g-3" id="divDetail${divDetailsCount}">
			<div class="col-auto">
				<label for="name" class="col-form-label">Name:</label>
			</div>
			<div class="col-auto">
				<input type="text" id="name" class="form-control" style="width: 400px;" name="detailNames">
			</div>

			<div class="col-auto" style="margin-left: 50px;">
				<label for="value" class="col-form-label">Value:</label>
			</div>
			<div class="col-auto">
				<input type="text" id="value" class="form-control" style="width: 400px;" name="detailValues">
			</div>
		</div>
		`;

	$("#divProductDetails").append(htmlDetailSection);

	previousDivDetailSection = allDivDetails.last();
	previousDivDetailID = previousDivDetailSection.attr("id");

	htmlLinkRemove = `
		<a class="btn fas fa-times-circle fa-2x icon-red"
			href="javascript:removeDetailSectionById('${previousDivDetailID}')"
			title="Remove this detail" style="width: 58px; "></a>
	`;

	previousDivDetailSection.append(htmlLinkRemove);

	$("input[name='detailNames']").last().focus();
}

function removeDetailSectionById(id) {
	$("#" + id).remove();
}

function removeDetailSectionByIndex(index) {
	$("#divDetail" + index).remove();
}