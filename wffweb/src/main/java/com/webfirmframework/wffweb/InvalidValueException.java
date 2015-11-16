/*
 * Copyright 2014-2015 Web Firm Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @author WFF
 */
package com.webfirmframework.wffweb;

/**
 *
 * @author WFF
 * @since 1.0.0
 */
public class InvalidValueException extends WffRuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -8083377221733123071L;

    public InvalidValueException() {
        super();
    }

    public InvalidValueException(final String arg0, final Throwable arg1,
            final boolean arg2, final boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public InvalidValueException(final String arg0, final Throwable arg1) {
        super(arg0, arg1);
    }

    public InvalidValueException(final String arg0) {
        super(arg0);
    }

    public InvalidValueException(final Throwable arg0) {
        super(arg0);
    }

}
