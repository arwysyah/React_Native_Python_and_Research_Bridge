import {TurboModule, TurboModuleRegistry} from 'react-native';


 export interface Spec extends TurboModule {
    getString(first:string,second:string):string
}

export default TurboModuleRegistry.getEnforcing<Spec>('TurboModules');
