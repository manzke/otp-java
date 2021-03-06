/*
Copyright 2013 Daniel Manzke (devsurf)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package de.devsurf.security.otp.api.internal;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import de.devsurf.security.otp.api.Hash;

public class Hmac {

    public static final String ALGORITHM = "RAW";

    private final String hash;

    private final byte[] secret;

    public Hmac( Hash hash, byte[] secret ) {
        this.hash = "HMAC" + hash.toString();
        this.secret = secret;
    }

    public byte[] digest( byte[] challenge )
        throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance( hash );
        SecretKeySpec macKey = new SecretKeySpec( secret, ALGORITHM );
        mac.init( macKey );
        return mac.doFinal( challenge );
    }
}
