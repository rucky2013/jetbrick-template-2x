/**
 * Copyright 2013-2014 Guoqiang Chen, Shanghai, China. All rights reserved.
 *
 *   Author: Guoqiang Chen
 *    Email: subchen@gmail.com
 *   WebURL: https://github.com/subchen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrick.template.resolver.function;

import jetbrick.bean.MethodInfo;
import jetbrick.template.JetSecurityManager;
import jetbrick.template.resolver.ParameterUtils;

/**
 * 访问 function(args, ...)
 */
final class ExtensionFunctionInvoker implements FunctionInvoker {
    private final MethodInfo method;
    private final int length;
    private final boolean isVarArgs;

    public ExtensionFunctionInvoker(MethodInfo method) {
        this.method = method;
        this.length = method.getParameterCount();
        this.isVarArgs = method.isVarArgs();
    }

    @Override
    public void checkAccess(JetSecurityManager securityManager) {
        securityManager.checkAccess(method.getMethod());
    }

    @Override
    public Object invoke(Object[] arguments) {
        arguments = ParameterUtils.getActualArguments(arguments, length, isVarArgs, 0);
        return method.invoke(null, arguments);
    }

    @Override
    public String getSignature() {
        return method.getSignature();
    }
}
