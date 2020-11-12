/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.graalvm.domain.resources;

import org.springframework.graalvm.json.JSONArray;
import org.springframework.graalvm.json.JSONObject;

/**
 * Converter to change resource descriptor objects into JSON objects
 *
 * @author Andy Clement
 */
class ResourcesJsonConverter {

	public JSONObject toJsonArray(ResourcesDescriptor metadata) throws Exception {
		JSONObject object = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (String p : metadata.getPatterns()) {
			jsonArray.put(toJsonObject(p));
		}
		JSONObject includes = new JSONObject();
		includes.put("includes", jsonArray);
		object.put("resources", includes);
		return object;
	}

	public JSONObject toJsonObject(String pattern) throws Exception {
		JSONObject object = new JSONObject();
		object.put("pattern", pattern);
		return object;
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("name", cd.getName());
//		Set<Flag> flags = cd.getFlags();
//		if (flags != null) {
//			for (Flag flag: Flag.values()) {
//				if (flags.contains(flag)) {
//					putTrueFlag(jsonObject,flag.name());
//				}
//			}
//		}
//		List<FieldDescriptor> fds = cd.getFields();
//		if (fds != null) {
//			JSONArray fieldJsonArray = new JSONArray();
//			for (FieldDescriptor fd: fds) {
//				JSONObject fieldjo = new JSONObject();
//				fieldjo.put("name", fd.getName());
//				if (fd.isAllowWrite()) {
//					fieldjo.put("allowWrite", "true");
//				}
//				fieldJsonArray.put(fieldjo);
//			}
//			jsonObject.put("fields", fieldJsonArray);
//		}
//		List<MethodDescriptor> mds = cd.getMethods();
//		if (mds != null) {
//			JSONArray methodsJsonArray = new JSONArray();
//			for (MethodDescriptor md: mds) {
//				JSONObject methodJsonObject = new JSONObject();
//				methodJsonObject.put("name", md.getName());
//				List<String> parameterTypes = md.getParameterTypes();
//					JSONArray parameterArray = new JSONArray();
//				if (parameterTypes != null) {
//					for (String pt: parameterTypes) {
//						parameterArray.put(pt);
//					}
//				}
//					methodJsonObject.put("parameterTypes",parameterArray);
//				methodsJsonArray.put(methodJsonObject);
//			}
//			jsonObject.put("methods", methodsJsonArray);
//		}
//		return jsonObject;
	}

//	private void putTrueFlag(JSONObject jsonObject, String name) throws Exception {
//		jsonObject.put(name, true);
//	}
}
