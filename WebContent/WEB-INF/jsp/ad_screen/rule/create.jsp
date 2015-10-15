<%@include file="../../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="_pageTitle" value="创建插屏广告应用规则" scope="request"/>
<c:set var="_underAdScreenRule" value="active" scope="request"/>
<c:set var="_activeAdScreen" value="active" scope="request"/>
<c:set var="_module" value="adScreen" scope="request"/>
<c:import url="../../theme/${_theme}/header.jsp"></c:import>
<link rel="stylesheet" href="${basePath}static/jquery/jquery-ui.css" />
<!-- main content -->
		<div class="page-header"><h1>创建/修改插屏广告应用规则</h1></div>
		<div id="pageContent">
			<c:import url="../../theme/${_theme}/errors.jsp"></c:import>
			<form action="${basePath}ad_screen/rule/save" onsubmit="javascript:onSubmitAppRuleForm()" method="post" class="form-horizontal">
				<input name="id" type="hidden" value="${form.id}">
                <input name="pageRules" type="hidden" id="pageRules">
				<input name="_queryString" type="hidden" value="${param.queryString}">
                <div class="control-group required-field">
                    <label class="control-label">名称:</label>
                    <div class="controls">
                        <input name="name" value="${fn:escapeXml(form.name)}" type="text" class="input-large">
                    </div>
                </div>
                <div class="control-group required-field">
                    <label class="control-label">包名:</label>
                    <div class="controls">
                        <input name="pkgName" value="${fn:escapeXml(form.pkgName)}" type="text" class="input-large">
                    </div>
                </div>
                <div class="control-group required-field">
                    <label class="control-label">页面规则:</label>
                    <div class="controls">
                        <div id="_div_page_rules">
                            <c:forEach var="pageRule" items="${form.pageRulesObject}" varStatus="vs">
                                <div class="page-rules">
                                    <div class="alert alert-success" style="display: inline-block;min-width: 300px;">
                                        <span class="close" data-dismiss="alert" onclick="javascript:deletePageRule(${vs.index})" ><i class="icon-trash"></i></span>
                                        <span class="close" onclick="javascript:editPageRule(${vs.index})" ><i class="icon-edit" style="margin-right: 10px;"></i></span>
                                        <ul>
                                            <li><span>页面类：</span><span id="_pr_activity_${vs.index}">${pageRule.activity}</span></li>
                                            <li><span>展示次数：</span><span id="_pr_maxTimes_${vs.index}">${pageRule.max_times}</span></li>
                                            <li><span>展示时间间隔：</span><span id="_pr_showTimeInterval_${vs.index}">${pageRule.show_time_interval}</span></li>
                                            <li><span>from：</span><span class="breakWord" id="_pr_from_${vs.index}">${pageRule.fromAsString}</span></li>
                                            <li><span>广告类型1：</span><span id="_pr_sdkType_${vs.index}">${sdkTypeMap[pageRule.sdk_type].name}</span></li>
                                            <c:forEach var="entry" items="${sdkTypeMap}">
                                                    <c:set var="sdk" value="${entry.value}"/>
                                                    <c:set var="display" value="${sdk.sdkType==pageRule.sdk_type}"/>
                                                    <c:forEach var="f" items="${sdk.fields}">
                                                        <li ${display?"":"style=\"display: none;\""}><span>${f.displayName}：</span><span id="_pr_${f.formId}_${vs.index}">${display?(f.selectable?f.optionsKVMap[pageRule.ad[f.jsonName]]:pageRule.ad[f.jsonName]):""}</span></li>
                                                    </c:forEach>
                                            </c:forEach>
                                        	<li><span>国家：</span><span id="_pr_countries_${vs.index}">${pageRule.countriesAsString}</span></li>
                                        </ul>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <a href="#_div_page_rule_modal" role="button" class="btn" data-toggle="modal" onclick="javascript:addPageRule()">新增页面规则</a>
                        <div id="_div_page_rule_modal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <span class="remark">新增/修改页面规则</span>
                            </div>
                            <div class="modal-body">
                                <div class="control-group required-field">
                                    <label class="control-label">页面类:</label>
                                    <div class="controls">
                                        <input id="_pr_activity" name="activity" type="text" class="input-medium">
                                        <span class="remark">如：com.qq.weixin</span>
                                    </div>
                                </div>
                                <div class="control-group required-field">
                                    <label class="control-label">展示次数:</label>
                                    <div class="controls">
                                        <input id="_pr_maxTimes" name="maxTimes" type="text" class="input-medium">
                                        <span class="remark">每天最多展示次数如：2</span>
                                    </div>
                                </div>
                                <div class="control-group required-field">
                                    <label class="control-label">展示时间间隔:</label>
                                    <div class="controls">
                                        <input id="_pr_showTimeInterval" name="showTimeInterval" type="text" class="input-medium">
                                        <div class="remark">两次广告展示时间的间隔，单位为秒如：3600</div>
                                    </div>
                                </div>
                                <div class="control-group required-field">
                                    <label class="control-label">from:</label>
                                    <div class="controls">
                                        <textarea id="_pr_from" name="from" type="text" class="input-large"></textarea>
                                        <div class="remark">多个from以“,”分隔，如：9871,9872留空默认全推</div>
                                    </div>
                                </div>
                                 <div class="control-group required-field">
                                    <label class="control-label">广告类型2:</label>
                                    <div class="controls">
                                        <select name="sdkType" id="_pr_sdkType" class="input-large" onchange="javascript:changeType();">
                                            <c:forEach var="entry" items="${sdkTypeMap}">
                                                <option value="${entry.key}">${entry.value.name}</option>
                                            </c:forEach>
								        </select>
                                    </div>
                                </div>

                                <c:forEach var="entry" items="${sdkTypeMap}">
                                    <c:set var="sdk" value="${entry.value}"/>
                                    <c:set var="display" value="${sdk.sdkType==pageRule.sdk_type}"/>
                                    <c:forEach var="f" items="${sdk.fields}">
                                        <div id="_cg_${f.formId}" class="control-group required-field">
                                            <label class="control-label">${f.displayName}:</label>
                                            <div class="controls">
                                                <c:choose>
                                                    <c:when test="${f.selectable}">
                                                        <select id="_pr_${f.formId}" name="${f.formId}" class="input-large">
                                                            <c:forEach var="opt" items="${f.options}">
                                                                <option value="${opt.value}">${opt.text}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input id="_pr_${f.formId}" name="${f.formId}" type="text" class="input-large">
                                                    </c:otherwise>
                                                </c:choose>
                                                <span class="remark">${f.remark}</span>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:forEach>
				                <div class="control-group required-field">
				                    <label class="control-label">国家:</label>
				                    <div class="controls">
				                        <a id="selectCountryLink" href="#selectCountryModal" role="button" class="btn" data-toggle="modal" onclick="javascript:onSearchCountry()">选择</a>
                                        <span class="remark">留空默认为通用所有国家</span>
				                        <input name="supportCountries" type="hidden">
				                        <div id="selectedCountriesDiv">
				                        </div>
				                        <div id="selectCountryModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				                            <div class="modal-header">
				                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				                                <div class="input-append">
				                                    <input class="input-large" name="countryKeyword" id="countryKeyword" type="text">
				                                    <button class="btn" type="button" onclick="javascript:onSearchCountry()">搜索</button>
				                                </div>
				                                <span class="remark">空的关键字可查询所有国家</span>
				                            </div>
				                            <div class="modal-body">
				                                <div id="countryUl"> 
				                                </div>
				                            </div>
				                            <div class="modal-footer">
				                                <span class="pull-left">
				                                    <a class="btn" onclick="onSelectAllCountry()">全选</a>
				                                    <a class="btn" onclick="onUnSelectAllCountry()">重置</a>
				                                </span>
				                                <button type="button" class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
				                                <button class="btn btn-primary" type="button" onclick="javascript:onAddCountry()">确定</button>
				                            </div>
				                        </div>
				                    </div>
				                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                                <button class="btn btn-primary" type="button" onclick="javascript:onSavePageRule()">确定</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="control-group required-field">
                    <label class="control-label">状态:</label>
                    <div class="controls">
                        <select id="sel_status" name="status" class="input-small">
                            <option value="1">推广</option>
                            <option value="0">暂停</option>
                            <option value="2">测试</option>
                        </select>
                        <script>document.getElementById("sel_status").value="${empty form.status?'1':form.status}"</script>
                    </div>
                </div>
				<div class="control-group required-field">
				  <label class="control-label">备注:</label>
				  <div class="controls">
				    <input name="remark" value="${fn:escapeXml(form.remark)}" type="text" class="input-large">
				  </div>
				</div>
				<div class="form-actions">
				  <input class="btn btn-primary" type="submit" value="保存">
				  <button type="button" class="btn" onclick="javascript:history.go(-1)">取消</button>
				</div>
			</form>
        </div>
<!-- end main content -->
<c:import url="../../theme/${_theme}/footer.jsp"></c:import>
<script>
var countries=[];
countries.push({code:"ae", chineseName: "阿联酋"});
countries.push({code:"af", chineseName: "阿富汗"});
countries.push({code:"ag", chineseName: "安提瓜和巴布达"});
countries.push({code:"ai", chineseName: "安圭拉"});
countries.push({code:"al", chineseName: "阿尔巴尼亚"});
countries.push({code:"am", chineseName: "亚美尼亚"});
countries.push({code:"ao", chineseName: "安哥拉"});
countries.push({code:"ar", chineseName: "阿根廷"});
countries.push({code:"as", chineseName: "美属萨摩亚"});
countries.push({code:"at", chineseName: "奥地利"});
countries.push({code:"au", chineseName: "澳大利亚"});
countries.push({code:"aw", chineseName: "阿鲁巴"});
countries.push({code:"az", chineseName: "阿塞拜疆"});
countries.push({code:"ba", chineseName: "波斯尼亚和黑塞哥维那"});
countries.push({code:"bb", chineseName: "巴巴多斯"});
countries.push({code:"bd", chineseName: "孟加拉"});
countries.push({code:"be", chineseName: "比利时"});
countries.push({code:"bf", chineseName: "布基纳法索"});
countries.push({code:"bg", chineseName: "保加利亚"});
countries.push({code:"bh", chineseName: "巴林"});
countries.push({code:"bi", chineseName: "布隆迪"});
countries.push({code:"bj", chineseName: "贝宁"});
countries.push({code:"bm", chineseName: "百慕大"});
countries.push({code:"bn", chineseName: "文莱"});
countries.push({code:"bo", chineseName: "玻利维亚"});
countries.push({code:"br", chineseName: "巴西"});
countries.push({code:"bs", chineseName: "巴哈马"});
countries.push({code:"bt", chineseName: "不丹"});
countries.push({code:"bw", chineseName: "博茨瓦纳"});
countries.push({code:"by", chineseName: "白俄罗斯"});
countries.push({code:"bz", chineseName: "伯利兹"});
countries.push({code:"ca", chineseName: "加拿大"});
countries.push({code:"cd", chineseName: "刚果"});
countries.push({code:"cf", chineseName: "中非"});
countries.push({code:"ch", chineseName: "瑞士"});
countries.push({code:"ci", chineseName: "科特迪瓦"});
countries.push({code:"ck", chineseName: "库克群岛"});
countries.push({code:"cl", chineseName: "智利"});
countries.push({code:"cm", chineseName: "喀麦隆"});
countries.push({code:"cn", chineseName: "中国"});
countries.push({code:"co", chineseName: "哥伦比亚"});
countries.push({code:"cr", chineseName: "哥斯达黎加"});
countries.push({code:"cu", chineseName: "古巴"});
countries.push({code:"cv", chineseName: "佛得角"});
countries.push({code:"cy", chineseName: "塞浦路斯"});
countries.push({code:"cz", chineseName: "捷克"});
countries.push({code:"de", chineseName: "德国"});
countries.push({code:"dj", chineseName: "吉布提"});
countries.push({code:"dk", chineseName: "丹麦"});
countries.push({code:"dm", chineseName: "多米尼克"});
countries.push({code:"do", chineseName: "多米尼加"});
countries.push({code:"dz", chineseName: "阿尔及利亚"});
countries.push({code:"ec", chineseName: "厄瓜多尔"});
countries.push({code:"ee", chineseName: "爱沙尼亚"});
countries.push({code:"eg", chineseName: "埃及"});
countries.push({code:"er", chineseName: "厄立特里亚"});
countries.push({code:"es", chineseName: "西班牙"});
countries.push({code:"et", chineseName: "埃塞俄比亚"});
countries.push({code:"fi", chineseName: "芬兰"});
countries.push({code:"fj", chineseName: "斐济"});
countries.push({code:"fm", chineseName: "密克罗尼西亚联邦"});
countries.push({code:"fo", chineseName: "法罗群岛"});
countries.push({code:"fr", chineseName: "法国"});
countries.push({code:"ga", chineseName: "加蓬"});
countries.push({code:"gb", chineseName: "英国"});
countries.push({code:"gd", chineseName: "格林纳达"});
countries.push({code:"ge", chineseName: "格鲁吉亚"});
countries.push({code:"gf", chineseName: "法属圭亚那"});
countries.push({code:"gh", chineseName: "加纳"});
countries.push({code:"gi", chineseName: "直布罗陀"});
countries.push({code:"gl", chineseName: "格陵兰"});
countries.push({code:"gm", chineseName: "冈比亚"});
countries.push({code:"gn", chineseName: "几内亚"});
countries.push({code:"gp", chineseName: "瓜德罗普"});
countries.push({code:"gq", chineseName: "赤道几内亚"});
countries.push({code:"gr", chineseName: "希腊"});
countries.push({code:"gt", chineseName: "危地马拉"});
countries.push({code:"gu", chineseName: "关岛"});
countries.push({code:"gy", chineseName: "圭亚那"});
countries.push({code:"hk", chineseName: "香港"});
countries.push({code:"hn", chineseName: "洪都拉斯"});
countries.push({code:"hr", chineseName: "克罗地亚"});
countries.push({code:"ht", chineseName: "海地"});
countries.push({code:"hu", chineseName: "匈牙利"});
countries.push({code:"id", chineseName: "印度尼西亚"});
countries.push({code:"ie", chineseName: "爱尔兰"});
countries.push({code:"il", chineseName: "以色列"});
countries.push({code:"in", chineseName: "印度"});
countries.push({code:"iq", chineseName: "伊拉克"});
countries.push({code:"ir", chineseName: "伊朗"});
countries.push({code:"is", chineseName: "冰岛"});
countries.push({code:"it", chineseName: "意大利"});
countries.push({code:"jm", chineseName: "牙买加"});
countries.push({code:"jo", chineseName: "约旦"});
countries.push({code:"jp", chineseName: "日本"});
countries.push({code:"ke", chineseName: "肯尼亚"});
countries.push({code:"kg", chineseName: "吉尔吉斯斯坦"});
countries.push({code:"kh", chineseName: "柬埔寨"});
countries.push({code:"ki", chineseName: "基里巴斯"});
countries.push({code:"km", chineseName: "科摩罗"});
countries.push({code:"kn", chineseName: "圣基茨和尼维斯"});
countries.push({code:"kp", chineseName: "朝鲜"});
countries.push({code:"kr", chineseName: "韩国"});
countries.push({code:"kw", chineseName: "科威特"});
countries.push({code:"ky", chineseName: "开曼群岛"});
countries.push({code:"kz", chineseName: "哈萨克斯坦"});
countries.push({code:"la", chineseName: "老挝"});
countries.push({code:"lb", chineseName: "黎巴嫩"});
countries.push({code:"lc", chineseName: "圣卢西亚"});
countries.push({code:"li", chineseName: "列支敦士登"});
countries.push({code:"lk", chineseName: "斯里兰卡"});
countries.push({code:"lr", chineseName: "利比里亚"});
countries.push({code:"ls", chineseName: "莱索托"});
countries.push({code:"lt", chineseName: "立陶宛"});
countries.push({code:"lv", chineseName: "拉脱维亚"});
countries.push({code:"ly", chineseName: "利比亚"});
countries.push({code:"ma", chineseName: "摩洛哥"});
countries.push({code:"mc", chineseName: "摩纳哥"});
countries.push({code:"md", chineseName: "摩尔多瓦"});
countries.push({code:"me", chineseName: "黑山"});
countries.push({code:"mg", chineseName: "马达加斯加"});
countries.push({code:"mh", chineseName: "马绍尔群岛"});
countries.push({code:"mk", chineseName: "卢森堡"});
countries.push({code:"ml", chineseName: "马里"});
countries.push({code:"mm", chineseName: "缅甸"});
countries.push({code:"mn", chineseName: "蒙古"});
countries.push({code:"mo", chineseName: "澳门"});
countries.push({code:"mp", chineseName: "北马里亚纳群岛"});
countries.push({code:"mq", chineseName: "马提尼克"});
countries.push({code:"mr", chineseName: "毛里塔尼亚"});
countries.push({code:"ms", chineseName: "蒙塞拉特岛"});
countries.push({code:"mt", chineseName: "马耳他"});
countries.push({code:"mu", chineseName: "毛里求斯"});
countries.push({code:"mv", chineseName: "马尔代夫"});
countries.push({code:"mw", chineseName: "马拉维"});
countries.push({code:"mx", chineseName: "墨西哥"});
countries.push({code:"my", chineseName: "马来西亚"});
countries.push({code:"mz", chineseName: "莫桑比克"});
countries.push({code:"na", chineseName: "纳米比亚"});
countries.push({code:"nc", chineseName: "新喀里多尼亚"});
countries.push({code:"ne", chineseName: "尼日尔"});
countries.push({code:"ng", chineseName: "尼日利亚"});
countries.push({code:"ni", chineseName: "尼加拉瓜"});
countries.push({code:"nl", chineseName: "荷兰"});
countries.push({code:"no", chineseName: "挪威"});
countries.push({code:"np", chineseName: "尼泊尔"});
countries.push({code:"nr", chineseName: "瑙鲁"});
countries.push({code:"nu", chineseName: "纽埃"});
countries.push({code:"nz", chineseName: "新西兰"});
countries.push({code:"om", chineseName: "阿曼"});
countries.push({code:"pa", chineseName: "巴拿马"});
countries.push({code:"pe", chineseName: "秘鲁"});
countries.push({code:"pf", chineseName: "法属波利尼西亚"});
countries.push({code:"pg", chineseName: "巴布亚新几内亚"});
countries.push({code:"ph", chineseName: "菲律宾"});
countries.push({code:"pk", chineseName: "巴基斯坦"});
countries.push({code:"pl", chineseName: "波兰"});
countries.push({code:"pm", chineseName: "圣皮埃尔和密克隆"});
countries.push({code:"pr", chineseName: "波多黎各"});
countries.push({code:"ps", chineseName: "巴勒斯坦"});
countries.push({code:"pt", chineseName: "葡萄牙"});
countries.push({code:"pw", chineseName: "帕劳"});
countries.push({code:"py", chineseName: "巴拉圭"});
countries.push({code:"qa", chineseName: "卡塔尔"});
countries.push({code:"re", chineseName: "留尼汪"});
countries.push({code:"ro", chineseName: "罗马尼亚"});
countries.push({code:"rs", chineseName: "塞尔维亚"});
countries.push({code:"ru", chineseName: "俄罗斯"});
countries.push({code:"rw", chineseName: "卢旺达"});
countries.push({code:"sa", chineseName: "沙特阿拉伯"});
countries.push({code:"sb", chineseName: "所罗门群岛"});
countries.push({code:"sc", chineseName: "塞舌尔"});
countries.push({code:"sd", chineseName: "苏丹"});
countries.push({code:"se", chineseName: "瑞典"});
countries.push({code:"sg", chineseName: "新加坡"});
countries.push({code:"si", chineseName: "斯洛文尼亚"});
countries.push({code:"sk", chineseName: "斯洛伐克"});
countries.push({code:"sl", chineseName: "塞拉利昂"});
countries.push({code:"sm", chineseName: "圣马力诺"});
countries.push({code:"sn", chineseName: "塞内加尔"});
countries.push({code:"so", chineseName: "索马里"});
countries.push({code:"sr", chineseName: "苏里南"});
countries.push({code:"st", chineseName: "圣多美和普林西比"});
countries.push({code:"sv", chineseName: "萨尔瓦多"});
countries.push({code:"sy", chineseName: "叙利亚"});
countries.push({code:"sz", chineseName: "斯威士兰"});
countries.push({code:"tc", chineseName: "特克斯和凯科斯群岛"});
countries.push({code:"td", chineseName: "乍得"});
countries.push({code:"tg", chineseName: "多哥"});
countries.push({code:"th", chineseName: "泰国"});
countries.push({code:"tj", chineseName: "塔吉克斯坦"});
countries.push({code:"tl", chineseName: "东帝汶"});
countries.push({code:"tm", chineseName: "土库曼斯坦"});
countries.push({code:"tn", chineseName: "突尼斯"});
countries.push({code:"to", chineseName: "汤加"});
countries.push({code:"tr", chineseName: "土耳其"});
countries.push({code:"tt", chineseName: "特立尼达和多巴哥"});
countries.push({code:"tw", chineseName: "台湾"});
countries.push({code:"tz", chineseName: "坦桑尼亚"});
countries.push({code:"ua", chineseName: "乌克兰"});
countries.push({code:"ug", chineseName: "乌干达"});
countries.push({code:"us", chineseName: "美国"});
countries.push({code:"uy", chineseName: "乌拉圭"});
countries.push({code:"uz", chineseName: "乌兹别克斯坦"});
countries.push({code:"va", chineseName: "梵蒂冈"});
countries.push({code:"vc", chineseName: "圣文森特和格林纳丁斯"});
countries.push({code:"ve", chineseName: "委内瑞拉"});
countries.push({code:"vg", chineseName: "英属维尔京群岛"});
countries.push({code:"vi", chineseName: "美属维尔京群岛"});
countries.push({code:"vn", chineseName: "越南"});
countries.push({code:"vu", chineseName: "瓦努阿图"});
countries.push({code:"wf", chineseName: "瓦利斯和富图纳"});
countries.push({code:"ws", chineseName: "萨摩亚"});
countries.push({code:"ye", chineseName: "也门"});
countries.push({code:"za", chineseName: "南非"});
countries.push({code:"zm", chineseName: "赞比亚"});
countries.push({code:"zw", chineseName: "津巴布韦"});

var countryCode2ChName={};
for(var idx =0;idx<countries.length;idx++){
    var c = countries[idx];
    countryCode2ChName[c.code]= c.chineseName;
}
function onSearchCountry(){
    var kw = $("#countryKeyword").val();
        var ul = $("#countryUl");
        ul.html("");
        var idx=0;
        var countryInput=$("input[name=supportCountries]");
        var supportedCountries = countryInput.val()==""?[]:countryInput.val().split(",");
        for(;idx<countries.length;idx++){
            var c= countries[idx];
            var flag=true;
            if(kw == ""){
                if(supportedCountries.length>0){
                    for(var i=0;i<supportedCountries.length;i++){
	                    if(supportedCountries[i]==c.code){
		                    ul.append($("<label class=\"checkbox inline\"> <input type=checkbox value=\""+ c.code+"\" checked=true>"+ c.chineseName+"</label>"));
	                    	flag=false;
	                    	break;
	                    }
                    }
                    if(flag){
                    	ul.append($("<label class=\"checkbox inline\"> <input type=checkbox value=\""+ c.code+"\" >"+ c.chineseName+"</label>"));
                    	flag = true;
                    }
                }else{
                	 ul.append($("<label class=\"checkbox inline\"> <input type=checkbox value=\""+ c.code+"\" >"+ c.chineseName+"</label>"));
                }
            }else{
            	if(supportedCountries.length>0){
                    for(var i=0;i<supportedCountries.length;i++){
	                    if(supportedCountries[i]==c.code&&c.chineseName.indexOf(kw)>=0){
		                    ul.append($("<label class=\"checkbox inline\"> <input type=checkbox value=\""+ c.code+"\" checked=true>"+ c.chineseName+"</label>"));
	                    	flag=false;
	                    	break;
	                    }else if(flag&&c.chineseName.indexOf(kw)>=0){
	                    	ul.append($("<label class=\"checkbox inline\"> <input type=checkbox value=\""+ c.code+"\" >"+ c.chineseName+"</label>"));
	                    	flag = false;
	                    	break;
	                    }
                    }
                }else{
                	if(flag&&c.chineseName.indexOf(kw)>=0){
                    	ul.append($("<label class=\"checkbox inline\"> <input type=checkbox value=\""+ c.code+"\" >"+ c.chineseName+"</label>"));
                    	flag = true;
                    }
                }
            }
            }
	}
	function onAddCountry(){
	    var countryInput=$("input[name=supportCountries]");
	    countryInput.val('');
	    var countries = countryInput.val()==""?[]:countryInput.val().split(",");
	    $("#countryUl input").each(function(idx, elem){
	        if(elem.checked){
	            var val = elem.value
	            if ($.inArray(val, countries) == -1){
	                countries.push(val);
	            }
	        }
	    });
	    countryInput.val(countries.join(","));
	    renderSelectedCountries(countries);
	    $('#selectCountryModal').modal('hide');
	}
	
	function renderSelectedCountries(countries){
	    var scd = $("#selectedCountriesDiv");
	    scd.html("");
	    var idx=0;
	    for(;idx<countries.length;idx++){
	        var c = countries[idx];
	        scd.append("<span class=\"delLable\">"+countryCode2ChName[c]+"<a href=\"#\" onclick=\"javascript:delCountry(this, '"+c+"')\" class=\"delIcon\">×</a></span>");
	    }
	}
	
	function initSelectedCountries(){
	    var countryInput=$("input[name=supportCountries]");
	    var countries = countryInput.val()==""?[]:countryInput.val().split(",");
	    renderSelectedCountries(countries);
	}
	
	function delCountry(elem, country){
	    var countryInput=$("input[name=supportCountries]");
	    var countries = countryInput.val()==""?[]:countryInput.val().split(",");
	    countries.splice($.inArray(country, countries),1);
	    countryInput.val(countries.join(","));
	    $(elem).parent("span").remove();
	}
	
	function onSelectAllCountry(){
	    $("#countryUl input").each(function(idx, elem){
	        elem.checked=true;
	    });
	}
	
	function onUnSelectAllCountry(){
	    $("#countryUl input").each(function(idx, elem){
	        elem.checked=false;
	    });
	}
	$("#countryKeyword").keypress(function(event) {
	    if ( event.which == 13 ) {
	        event.preventDefault();
	        onSearchCountry();
	    }
	});
	$.ready(initSelectedCountries());
    var editIdx=-1;//用于标志当前编辑的page rule
    var pageRules=[];
    <c:forEach var="pageRule" items="${form.innerPageRulesObject}" varStatus="vs">
    pageRules.push(${pageRule.jsonString});
    </c:forEach>
    var sdkTypeNames={}
    var optionNames={}
    <c:forEach var="entry" items="${sdkTypeMap}">
        sdkTypeNames[${entry.key}]="${entry.value.name}";
        <c:forEach var="f" items="${entry.value.fields}">
            <c:if test="${f.selectable}">
                <c:forEach var="opt" items="${f.options}">
                optionNames["${entry.key}-${f.formId}-${opt.value}"]="${opt.text}";
                </c:forEach>
            </c:if>
        </c:forEach>
    </c:forEach>
    function onSavePageRule(){
        var activity=$("#_pr_activity").val();
        var maxTimes=$("#_pr_maxTimes").val();
        var showTimeInterval=$("#_pr_showTimeInterval").val();
        var from=$("#_pr_from").val();
        var sdkType=$("#_pr_sdkType").val();
        var sdkTypeName= sdkTypeNames[sdkType];
        <c:forEach var="entry" items="${sdkTypeMap}">
            <c:forEach var="f" items="${entry.value.fields}">
            var ${f.formId}=$("#_pr_${f.formId}").val();
            </c:forEach>
        </c:forEach>
        var _countries=$("input[name=supportCountries]").val();
        var countries = _countries==""?[]:_countries.split(",");
        var countries2ChineseName="";
        for(idx=0;idx<countries.length;idx++){
	        var c = countries[idx];
	        if(countries2ChineseName!=""){
	        	countries2ChineseName = countries2ChineseName+","+countryCode2ChName[c];
	        }else{
	        	countries2ChineseName =countryCode2ChName[c];
	        }
	    }
        if(!/^[a-zA-Z0-9][a-zA-Z0-9\.]+[a-zA-Z0-9]$/.test(activity)){
            alert("页面类格式不正确");
            return;
        }
        if(!/^\d+$/.test(maxTimes)){
            alert("展示次数格式不正确");
            return;
        }
        if(!/^\d+$/.test(showTimeInterval)){
            alert("展示时间间隔格式不正确");
            return;
        }
        <c:forEach var="entry" items="${sdkTypeMap}">
        <c:set var="sdk" value="${entry.value}"/>
        if(sdkType == ${sdk.sdkType}) {
            <c:forEach var="f" items="${entry.value.fields}">
            if(/^\s*$/.test(${f.formId})){
                alert("${f.displayName}不能为空");
                return;
            }
            </c:forEach>
        }
        </c:forEach>

        var idx=editIdx==-1?pageRules.length:editIdx;
        var html='<div class="page-rules">'+
                '<div class="alert alert-success" style="display: inline-block;min-width: 300px;">'+
                '<span class="close" data-dismiss="alert" onclick="javascript:deletePageRule('+idx+')" ><i class="icon-trash"></i></span>'+
                '<span class="close" onclick="javascript:editPageRule('+idx+')" ><i class="icon-edit" style="margin-right: 10px;"></i></span>'+
                '<ul>'+
                '<li><span>页面类：</span><span id="_pr_activity_'+idx+'">'+activity+'</span></li>'+
                '<li><span>展示次数：</span><span id="_pr_maxTimes_'+idx+'">'+maxTimes+'</span></li>'+
                '<li><span>展示时间间隔：</span><span id="_pr_showTimeInterval_'+idx+'">'+showTimeInterval+'</span></li>'+
                '<li><span>from：</span><span id="_pr_from_'+idx+'">'+from+'</span></li>'+
                '<li><span>广告类型3：</span><span id="_pr_sdkType_'+idx+'">'+sdkTypeName+'</span></li>';
                <c:forEach var="entry" items="${sdkTypeMap}">
                    <c:forEach var="f" items="${entry.value.fields}">
                    <c:if test="${f.selectable}">
                        <c:set var="optionJs" value="optionNames[\"${entry.key}-${f.formId}-\"+${f.formId}]"/>
                    </c:if>
                    html=html+'<li style="display:'+(sdkType==${entry.key}?"":"none")+'"><span>${f.formId}：</span><span id="_pr_${f.formId}_'+idx+'">'+${f.selectable?optionJs:f.formId}+'</span></li>';
                    </c:forEach>
                </c:forEach>
        html +='<li><span>国家：</span><span id="_pr_countries_'+idx+'">'+countries2ChineseName+'</span></li>'+
        		'</ul>'+
        		'</div>'+
        		'</div>';
        var pageRule = {"activity":activity,"max_times":parseInt(maxTimes),"show_time_interval":showTimeInterval,"from":from,"sdk_type":parseInt(sdkType),"countries":countries,"ad":{}};
        <c:forEach var="entry" items="${sdkTypeMap}">
        if(sdkType==${entry.key}) {
            <c:forEach var="f" items="${entry.value.fields}">
            pageRule.ad.${f.jsonName}=${f.formId};
            </c:forEach>
        }
        </c:forEach>
        if(editIdx==-1){
            pageRules.push(pageRule);
            controlCss(sdkType,idx);
            $("#_div_page_rules").append($(html));
        }else{
            pageRules[editIdx]=pageRule;
            $("#_pr_activity_"+editIdx).html(activity);
            $("#_pr_maxTimes_"+editIdx).html(maxTimes);
            $("#_pr_showTimeInterval_"+editIdx).html(showTimeInterval);
            $("#_pr_from_"+editIdx).html(from);
            $("#_pr_sdkType_"+editIdx).html(sdkTypeName);
            $("#_pr_countries_"+editIdx).html(countries2ChineseName);
            controlCss(sdkType,editIdx);
            <c:forEach var="entry" items="${sdkTypeMap}">
                if(sdkType==${entry.key}) {
                    <c:forEach var="f" items="${entry.value.fields}">
                    <c:if test="${f.selectable}">
                    <c:set var="optionJs" value="optionNames[\"${entry.key}-${f.formId}-\"+${f.formId}]"/>
                    </c:if>
                    $("#_pr_${f.formId}_"+editIdx).html(${f.selectable?optionJs:f.formId});
                    </c:forEach>
                }
            </c:forEach>
        }
        $('#_div_page_rule_modal').modal('hide');
    }
    function deletePageRule(idx){
        pageRules[idx]=null;
    }
    function editPageRule(idx){
        editIdx = idx;
        var pr=pageRules[idx];
        $("#_pr_activity").val(pr.activity);
        $("#_pr_maxTimes").val(pr.max_times);
        $("#_pr_showTimeInterval").val(pr.show_time_interval);
        $("#_pr_from").val(pr.from);
        $("#_pr_sdkType").val(pr.sdk_type);
        changeType();
        <c:forEach var="entry" items="${sdkTypeMap}">
            <c:forEach var="f" items="${entry.value.fields}">
                $("#_pr_${f.formId}").val(pr.ad.${f.jsonName});
            </c:forEach>
        </c:forEach>
        $("input[name=supportCountries]").val(pr.countries);
        initSelectedCountries();
        $('#_div_page_rule_modal').modal('show');
    }
    function addPageRule(){
        editIdx = -1;
        $("#_pr_activity").val("");
        $("#_pr_maxTimes").val("");
        $("#_pr_showTimeInterval").val("");
        $("#_pr_sdkType").val("1");
        $("#_pr_from").val("");
        setValue();
        changeType();
        $("input[name=supportCountries]").val("");
        initSelectedCountries();
    }
    function onSubmitAppRuleForm(){
        for(var i=0;i<pageRules.length;){
            if(pageRules[i]==null){
            	pageRules.splice(i,1);
            }else{
            	i++;
            }
        }
        $("#pageRules").val(JSON.stringify(pageRules));
    }
    function changeType(){
        var type=$("#_pr_sdkType").val();
        hideAll(2);
        <c:forEach var="entry" items="${sdkTypeMap}">
        if(type=="${entry.key}") {
            <c:forEach var="f" items="${entry.value.fields}">
            $("#_cg_${f.formId}").show();
            </c:forEach>
        }
        </c:forEach>
        setValue();
    }
    function setValue(){
        <c:forEach var="entry" items="${sdkTypeMap}">
            <c:forEach var="f" items="${entry.value.fields}">
                $("#_pr_${f.formId}").val("");
            </c:forEach>
        </c:forEach>
    }
    function controlCss(type,editIdx){
    	hideAll(1);
        <c:forEach var="entry" items="${sdkTypeMap}">
        if(type==${entry.key}) {
            <c:forEach var="f" items="${entry.value.fields}">
            $("#_pr_${f.formId}_"+editIdx).parent().css('display','');
            </c:forEach>
            return;
        }
        </c:forEach>
    }
    function hideAll(type){
    	if(type == 1){
            <c:forEach var="entry" items="${sdkTypeMap}">
                <c:forEach var="f" items="${entry.value.fields}">
                    $("#_pr_${f.formId}_"+editIdx).parent().css('display','none');
                </c:forEach>
            </c:forEach>
    	}else{
            <c:forEach var="entry" items="${sdkTypeMap}">
                <c:forEach var="f" items="${entry.value.fields}">
                    $("#_cg_${f.formId}").hide();
                </c:forEach>
            </c:forEach>
    	}
    }
    
</script>
