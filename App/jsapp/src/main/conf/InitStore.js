/*
_init_: 初始值，不会应被redux中以返回Object.assign为处理结果的reduce覆盖（不使用{...data}返回结果）
*/
export default {
    app: {
        _init_: {
            version: {
                current: 'v1.0.0'
            }
        },
        welcome: true,
    }
}
