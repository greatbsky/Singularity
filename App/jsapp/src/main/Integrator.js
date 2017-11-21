import {applyMiddleware, createStore, compose} from 'redux';
import {Provider} from 'react-redux';
import {persistStore, persistCombineReducers} from 'redux-persist';
import storage from 'redux-persist/lib/storage';
import {PersistGate} from 'redux-persist/lib/integration/react';
import thunk from 'redux-thunk';

import reducers from './dao/reducers';
import initStore from './conf/InitStore';

export const integrateRedux = (persistedCB) => {
    var middlewares = [thunk];
    if (process.env.NODE_ENV === `development`) { //Log only in development
        const { logger } = require(`redux-logger`);
        middlewares.push(logger);
    }
    const persistConfig = {
        key: 'root',
        storage,
        blacklist: [''],
        debug: true
    };
    let store = createStore(persistCombineReducers(persistConfig, reducers), initStore, compose(
        applyMiddleware(...middlewares)
    ));
    let persistor = persistStore(store, null, persistedCB);
    return {persistor, store};
};
