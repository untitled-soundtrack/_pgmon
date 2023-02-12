import { describe, it, expect, vi } from "vitest";
import { shallowMount } from "@vue/test-utils";
import { createTestingPinia } from "@pinia/testing";

import RegisterView from "@/views/RegisterView.vue";

describe("RegisterView.vue", () => {
  it("no field is allowed to be empty", async () => {
    const wrapper = shallowMount(RegisterView, {
      global: {
        plugins: [createTestingPinia({ createSpy: vi.fn })],
      },
    });
    await wrapper.setData({
      name: "",
      email: "",
      password: "",
      password2: "",
    });

    expect(wrapper.vm.v$.name.required.$invalid).toBe(true);
    expect(wrapper.vm.v$.email.required.$invalid).toBe(true);
    expect(wrapper.vm.v$.password.required.$invalid).toBe(true);
    expect(wrapper.vm.v$.password2.required.$invalid).toBe(true);
  });

  it("name must be at least 3, password at least 8 characters long", async () => {
    const wrapper = shallowMount(RegisterView, {
      global: {
        plugins: [createTestingPinia({ createSpy: vi.fn })],
      },
    });
    await wrapper.setData({
      name: "12",
      password: "1234567",
    });

    expect(wrapper.vm.v$.name.minLength.$invalid).toBe(true);
    expect(wrapper.vm.v$.password.minLength.$invalid).toBe(true);
  });

  it("password and password2 need to match", async () => {
    const wrapper = shallowMount(RegisterView, {
      global: {
        plugins: [createTestingPinia({ createSpy: vi.fn })],
      },
    });
    await wrapper.setData({
      password: "foo123456",
      password2: "bar123456",
    });

    expect(wrapper.vm.v$.password2.sameAsPassword.$invalid).toBe(true);
  });

  it("with correct data, the validation passes successfully", async () => {
    const wrapper = shallowMount(RegisterView, {
      global: {
        plugins: [createTestingPinia({ createSpy: vi.fn })],
      },
    });
    await wrapper.setData({
      name: "peter",
      email: "peter.urban@email.com",
      password: "foo12345",
      password2: "foo12345",
    });

    expect(wrapper.vm.v$.name.$invalid).toBe(false);
    expect(wrapper.vm.v$.email.$invalid).toBe(false);
    expect(wrapper.vm.v$.password.$invalid).toBe(false);
    expect(wrapper.vm.v$.password2.$invalid).toBe(false);
    expect(wrapper.vm.v$.$invalid).toBe(false);
  });
});
