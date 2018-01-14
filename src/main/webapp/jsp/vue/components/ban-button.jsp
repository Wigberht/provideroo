<script type="text/x-template" id="ban-button-template">
    <a @click="banToggle"
       class="waves-effect waves-light btn">{{buttonText}}</a>

</script>

<script>
    Vue.component('ban-button', {
        props: ['text_ban', 'text_unban', 'banned_state', 'user_id'],
        template: "#ban-button-template",
        data() {
            return {
                banned: false,
                toast: {
                    time: 1000,
                    text: {
                        ban: {
                            success: "Successful ban",
                            error: "Unsuccessful ban"
                        },
                        unban: {
                            success: "Successful unban",
                            error: "Unsuccessful unban"
                        }
                    }
                }
            }
        },
        methods: {
            banUser() {
                console.log(this.user_id);

                axios.post("/rest/user/ban", {
                    'userId': this.user_id,
                    'banned': !this.banned
                }).then((response) => {
                    console.log(response);
                    console.log(response.data);
                    if (response.data.success) {
                        Materialize.toast(this.toast.text, 1000)
                    } else {
                        Materialize.toast("Ban was unsuccessful", 1000)
                    }

                }).catch((error) => {
                    console.log(error);
                    Materialize.toast("Ban was unsuccessful", 1000)
                })
            },

            banToggle() {
                console.log(this.user_id);

                console.log("Banned now  : " + this.banned);
                console.log("Banned after: " + !this.banned);
                axios.post("/rest/user/ban", {
                    'userId': this.user_id,
                    'banned': !this.banned
                }).then((response) => {
                    console.log(response);
                    console.log(response.data);

                    if (response.data.success) {
                        this.banned = !this.banned;
                        Materialize.toast("Toggle success", this.toast.time)
                    } else {
                        Materialize.toast("Toggle fail", this.toast.time)
                    }
                }).catch((error) => {
                    console.log(error);
                    Materialize.toast("Toggle fail", this.toast.time);
                })
            },
        },
        mounted: function () {
            this.banned = this.banned_state == 'true';
            console.log("Banned: ", this.banned);
        },
        computed: {
            buttonText() {
                return this.banned ? this.text_unban : this.text_ban;
            }
        }
    })
</script>
