<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="form_bx" class="well form-horizontal">
	<fieldset>
		<legend>
			<img style="height: 30px;" src="img/bx.jpg" />&nbsp;冰箱
		</legend>
		<div class="control-group">
			<label class="control-label">描述</label>
			<div class="controls">
			
				<label class="radio"><input type="radio" name="bx_desc" checked
					value="150lt"/>150L以下 
				</label> 
				
				<label class="radio"> <input
					type="radio" name="bx_desc" value="150-190"/>150L-190L(含)
				</label> 
				<label class="radio"> <input type="radio" name="bx_desc"
					value="190-240"/>190L-240L(含)
				</label> <label class="radio"> <input type="radio" name="bx_desc"
					value="240gt"/>240L以上
				</label> <label class="radio"> <input type="radio" name="bx_desc"
					value="bg"/>冰柜
				</label>
				
			</div>
			<div class="control-group">
				<label class="control-label" for="count">数量</label>
				<div class="controls">
					<input type="text" name="count" value="1" id="count" placeholder="数量" />
				</div>
			</div>
		</div>

		<div class="control-group">
			<input type="button" value="添加冰箱" addto="bx" class="btn controls add" />

		</div>

	</fieldset>
</form>