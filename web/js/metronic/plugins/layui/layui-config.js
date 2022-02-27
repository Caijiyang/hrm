/*
作者：sjm
时间：2018-05-18
描述：开发时根据实际情况注释掉或更改
*/
var context="/hrm"

/**
 * 配置layui的路径
 */

layui.config({
	
	dir : 'js/metronic/plugins/layui/',
	version : false,
	debug : false,
	base : 'js/metronic/plugins/layui/expand/'
})
//.extend({
//    jqmodel: 'jqmodel',
//    jqtab: 'jqtab'
//})
;
layui.context = context;
