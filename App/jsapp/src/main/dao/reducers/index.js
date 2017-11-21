/**
注意：reducer 是纯函数。它仅仅用于计算下一个 state。它应该是完全可预测的：多次传入相同的输入必须产生相同的输出。它不应做有其他作用的操作，如 API 调用或路由跳转。
参数命名说明：
想象store.getState()是一个tree
root = RootState
ele = ElementState
*/
import app from './AppReducer';
import video from './VideoReducer';

export default {
    app: app,
    video: video
};
